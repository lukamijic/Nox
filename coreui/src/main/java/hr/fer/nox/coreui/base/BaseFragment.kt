package hr.fer.nox.coreui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import hr.fer.nox.navigation.routing.BackPropagatingFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.getKoin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

abstract class BaseFragment<ViewState> : Fragment(), BaseView, BackPropagatingFragment {

    private var disposables = CompositeDisposable()

    val scopeRetainer by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = ScopeRetainer(getKoin()) as T
        }).get(ScopeRetainer::class.java)
    }

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected abstract fun getScopeName(): String

    protected abstract fun render(viewState: ViewState)

    protected abstract fun getViewPresenter(): ViewPresenter<BaseView, ViewState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scopeRetainer.setScope(getScopeName())
        scopeRetainer.addDestroyable(getViewPresenter())
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(getLayoutResource(), container, false)

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposables = CompositeDisposable()
        initialiseView(view, savedInstanceState)
        getViewPresenter().viewAttached(this)
        addDisposable(getViewPresenter().viewState().subscribe(this::render))
    }

    override fun onDestroyView() {
        getViewPresenter().viewDetached()
        disposables.dispose()
        super.onDestroyView()
    }

    override fun back(): Boolean {
        getViewPresenter().back()
        return true
    }

    protected inline fun <reified T> scopedInject(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
    ): Lazy<T> = lazy { scopeRetainer.getScope().get<T>(qualifier = qualifier, parameters = parameters) }

    /**
     * Override to initialise view
     */
    protected open fun initialiseView(view: View, savedInstanceState: Bundle?) {
        // Template
    }

    /**
     * Override to observe view state
     */
    protected open fun observeViewState() {
        // Template
    }

    fun addDisposable(disposable: Disposable) {
        if (disposables.isDisposed) {
            throw UnsupportedOperationException("View disposables are disposed")
        }

        disposables.add(disposable)
    }
}