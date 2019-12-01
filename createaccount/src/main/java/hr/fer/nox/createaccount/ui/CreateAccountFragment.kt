package hr.fer.nox.createaccount.ui

import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.createaccount.R
import hr.fer.nox.createaccount.di.CREATE_ACCOUNT_VIEW_SCOPE
import kotlinx.android.synthetic.main.fragment_create_account.*

class CreateAccountFragment : BaseFragment<CreateAccountViewState>(), CreateAccountContract.View {

    companion object {

        const val TAG = "CreateAccountFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_create_account

        fun newInstance(): Fragment = CreateAccountFragment()
    }

    private val presenter: CreateAccountContract.Presenter by scopedInject()

    private var isPasswordShown = false

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        createaccount_nameInput.setButtonClickListener(View.OnClickListener { createaccount_nameInput.setText("") })
        createaccount_surnameInput.setButtonClickListener(View.OnClickListener { createaccount_surnameInput.setText("") })
        createaccount_emailInput.setButtonClickListener(View.OnClickListener { createaccount_emailInput.setText("") })

        createaccount_passwordInput.setButtonClickListener(View.OnClickListener { togglePassword(!isPasswordShown) })
        createaccount_passwordInput.showActionButton()
        createaccount_passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)

        createaccount_createAccountButton.setOnClickListener {
            presenter.createAccount(
                createaccount_nameInput.getText(),
                createaccount_surnameInput.getText(),
                createaccount_emailInput.getText(),
                createaccount_passwordInput.getText()
            )
        }

        createaccount_nameInput.showActionButton()
        createaccount_surnameInput.showActionButton()
        createaccount_emailInput.showActionButton()
    }

    override fun render(viewState: CreateAccountViewState) {
        with(viewState) {
            createaccount_progressView.isVisible = isLoading
            createaccount_errorText.text = errorMessage
            createaccount_errorText.isGone = errorMessage.isEmpty()
        }
    }

    private fun togglePassword(showPassword: Boolean) {
        if (showPassword) {
            createaccount_passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)
            createaccount_passwordInput.setActionButtonDrawableRes(R.drawable.ic_eye_active)
        } else {
            createaccount_passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            createaccount_passwordInput.setActionButtonDrawableRes(R.drawable.ic_eye_inactive)
        }
        createaccount_passwordInput.setCursorAtEnd()
        isPasswordShown = showPassword
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        createaccount_nameInput.restoreTextState()
        createaccount_surnameInput.restoreTextState()
        createaccount_passwordInput.restoreTextState()
    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = CREATE_ACCOUNT_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, CreateAccountViewState> = presenter as ViewPresenter<BaseView, CreateAccountViewState>
}