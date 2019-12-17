package hr.fer.nox.search.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.search.R
import hr.fer.nox.search.di.SEARCH_USERS_VIEW_SCOPE
import hr.fer.nox.search.model.UserItemViewModel
import hr.fer.nox.search.ui.users.adapter.UsersAdapter
import kotlinx.android.synthetic.main.fragment_search_users.*
import org.koin.android.ext.android.inject


class SearchUsersFragment: BaseFragment<SearchUsersViewState>(), SearchUsersContract.View {

    companion object {

        const val TAG = "SearchUsersFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_search_users

        private const val COLUMN_COUNT = 3

        fun newInstance(): Fragment = SearchUsersFragment()

    }

    private val presenter: SearchUsersContract.Presenter by scopedInject()
    private val imageUtils: ImageUtils by inject()

    private lateinit var usersAdapter: UsersAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        usersAdapter = UsersAdapter(
            LayoutInflater.from(context),
            imageUtils
        ) { userItemViewModel: UserItemViewModel -> presenter.showUserDetails(userItemViewModel.userId) }

        search_users_recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT, RecyclerView.VERTICAL, false)
            adapter = usersAdapter
        }

        search_users_searchBar.searchConsumer = { searchTerm -> presenter.searchUsers(searchTerm)}
        search_users_searchBar.setAutomaticSearchAction()
    }

    override fun render(viewState: SearchUsersViewState) {
        search_users_progressView.isVisible = viewState.isLoading
        search_users_noUsersFound.isVisible = viewState.users.isEmpty()
        usersAdapter.submitList(viewState.users)
    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = SEARCH_USERS_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, SearchUsersViewState> = presenter as ViewPresenter<BaseView, SearchUsersViewState>
}