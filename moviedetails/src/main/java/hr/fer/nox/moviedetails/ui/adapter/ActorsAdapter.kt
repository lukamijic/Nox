package hr.fer.nox.moviedetails.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.util.DiffUtilCallback
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.moviedetails.R
import hr.fer.nox.moviedetails.model.ActorViewModel
import kotlinx.android.synthetic.main.item_actor.view.*

@LayoutRes
private val LAYOUT_RESOURCE_ACTOR = R.layout.item_actor

class ActorsAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageUtils: ImageUtils
): ListAdapter<ActorViewModel, ActorsAdapter.ActorViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder =
        ActorViewHolder(layoutInflater.inflate(LAYOUT_RESOURCE_ACTOR, parent, false))

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) =
        holder.fillView(getItem(position))

    inner class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun fillView(item: ActorViewModel) {
            itemView.moviedetails_actorName.text = item.actorName
            itemView.moviedetails_actorRoleName.text = item.actorRoleName
            item.actorImageUrl?.run { imageUtils.loadInto(this, itemView.moviedetails_actorImage) }
        }
    }
}