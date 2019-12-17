package hr.fer.nox.search.ui.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.util.DiffUtilCallback
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.search.R
import hr.fer.nox.search.model.UserItemViewModel

class UsersAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageUtils: ImageUtils,
    private val userSelectedAction: (UserItemViewModel) -> Unit
): ListAdapter<UserItemViewModel, UsersAdapter.UserViewHolder>(DiffUtilCallback()) {

    companion object {

        @LayoutRes
        private val LAYOUT_RESOURCE_MOVIE = R.layout.item_users_user
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(layoutInflater.inflate(LAYOUT_RESOURCE_MOVIE, parent, false))


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.fillView(getItem(position))

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun fillView(item: UserItemViewModel) {
            itemView.setOnClickListener { userSelectedAction(item) }
        }
    }
}