package hr.fer.nox.login.ui

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.KeyboardUtils
import hr.fer.nox.coreui.util.KeyboardWatcher
import hr.fer.nox.login.R
import hr.fer.nox.login.di.LOGIN_VIEW_SCOPE
import hr.fer.nox.ui.ActionableInputView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginFragment: BaseFragment<LoginViewState>(), LoginContract.View {

    companion object {

        const val TAG = "LoginFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_login

        fun newInstance(): Fragment = LoginFragment()
    }

    private val presenter: LoginContract.Presenter by scopedInject()
    private val keyboardUtils: KeyboardUtils by inject()
    private val keyboardWatcher: KeyboardWatcher by inject(parameters = { parametersOf(requireActivity()) })

    private var viewDisposables = CompositeDisposable()

    private var isPasswordShown = false

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        login_usernameInput.setButtonClickListener(View.OnClickListener { login_usernameInput.setText("") })
        login_passwordInput.setButtonClickListener(View.OnClickListener { togglePassword(!isPasswordShown) })
        login_passwordInput.showActionButton()
        login_passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)

        login_button.setOnClickListener {
            keyboardUtils.hideSoftKeyboard(focusView)
            presenter.login(login_usernameInput.getText(), login_passwordInput.getText())
        }

        login_createAccount.setOnClickListener { presenter.showCreateAccount() }

        keyboardWatcher.initialize()
    }

    override fun render(viewState: LoginViewState) {
        viewState.apply {
            login_progressView.isGone = !isLoading
            login_errorText.text = errorMessage
            login_errorText.isGone = errorMessage.isEmpty()
        }
    }

    override fun onResume() {
        super.onResume()
        initializeViews()
    }

    private fun initializeViews() {
        viewDisposables = CompositeDisposable()
        viewDisposables.add(keyboardWatcher.keyboardListener().subscribe { keyboardShown -> handleKeyboardEvents(keyboardShown) })
        viewDisposables.add(login_usernameInput.focusChange().subscribe { focus -> handleCredentialsInputFocus(login_usernameInput, focus) })
        viewDisposables.add(login_passwordInput.focusChange().subscribe { focus -> handleCredentialsInputFocus(login_passwordInput, focus) })
    }

    override fun onPause() {
        viewDisposables.dispose()
        super.onPause()
    }

    override fun onDestroyView() {
        keyboardWatcher.destroy()
        super.onDestroyView()
    }

    private fun handleKeyboardEvents(keyboardShown: Boolean) {
        if (!keyboardShown) {
            setInitialViewPositions()
        }
    }

    private fun handleCredentialsInputFocus(view: ActionableInputView, focus: Boolean) = if (focus) enterEditMode(view) else exitEditMode(view)

    private fun enterEditMode(view: ActionableInputView) {
        focusGroup.visibility = View.GONE
        view.showActionButton()
    }

    private fun exitEditMode(view: ActionableInputView) {
        focusGroup.visibility = View.VISIBLE
        view.hideActionButton()
    }

    private fun setInitialViewPositions() {
        focusView.requestFocus()
    }

    private fun togglePassword(showPassword: Boolean) {
        if (showPassword) {
            login_passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            login_passwordInput.setActionButtonDrawableRes(R.drawable.ic_eye_active)
        } else {
            login_passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            login_passwordInput.setActionButtonDrawableRes(R.drawable.ic_eye_inactive)
        }
        login_passwordInput.setCursorAtEnd()
        isPasswordShown = showPassword
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        login_usernameInput.restoreTextState()
        login_passwordInput.restoreTextState()
    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = LOGIN_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, LoginViewState> = presenter as ViewPresenter<BaseView, LoginViewState>
}