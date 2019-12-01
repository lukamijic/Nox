package hr.fer.nox.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
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
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginFragment : BaseFragment<LoginViewState>(), LoginContract.View {

    companion object {

        const val TAG = "LoginFragment"

        private val RC_GOOGLE_SIGN_IN = 1337

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_login

        fun newInstance(): Fragment = LoginFragment()
    }

    private val presenter: LoginContract.Presenter by scopedInject()
    private val keyboardUtils: KeyboardUtils by inject()
    private val keyboardWatcher: KeyboardWatcher by inject(parameters = { parametersOf(requireActivity()) })
    private val googleSignInClient: GoogleSignInClient by scopedInject(parameters = { parametersOf(context) })

    private var viewDisposables = CompositeDisposable()

    private var isPasswordShown = false

    private lateinit var facebookCallbackManager: CallbackManager

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

        setupFacebookLogin()

        login_googleLogin.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, RC_GOOGLE_SIGN_IN)
        }

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

    private fun setupFacebookLogin() {
        facebookCallbackManager = CallbackManager.Factory.create()
        login_facebookLogin.fragment = this
        login_facebookLogin.setPermissions("email", "public_profile")
        login_facebookLogin.registerCallback(facebookCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                presenter.facebookLogin(result.accessToken)
            }

            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {

            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.run { presenter.googleLogin(account) }
            } catch (e: ApiException) {
                throw e
            }
        }
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