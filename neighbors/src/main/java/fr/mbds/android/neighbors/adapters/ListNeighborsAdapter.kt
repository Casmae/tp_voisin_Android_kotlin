package fr.mbds.android.neighbors.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.mbds.android.neighbors.R
import fr.mbds.android.neighbors.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>,
    var listNeighborHandler: ListNeighborHandler
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {
    private val mNeighbours: List<Neighbor> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.neighbor_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Neighbor = mNeighbours[position]
        val context = holder.mNeighbourAvatar.context
        // Display Neighbour Avatar
        Glide.with(context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(holder.mNeighbourAvatar)
        // Display Neighbour Name
        holder.mNeighbourName.text = neighbour.name
        holder.mDeleteButton.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle(R.string.neighborAlert)
            alertDialogBuilder.setMessage(R.string.deleteNeighborMessage)

            alertDialogBuilder.setPositiveButton(R.string.yes) { dialog, which ->
                Toast.makeText(
                    context,
                    R.string.yes, Toast.LENGTH_SHORT
                ).show()
                listNeighborHandler.onDeleteNeighbor(neighbour)
                // on refresh
            }

            alertDialogBuilder.setNegativeButton(R.string.no) { dialog, which ->
                Toast.makeText(
                    context,
                    R.string.no, Toast.LENGTH_SHORT
                ).show()
            }
            alertDialogBuilder.show()
        }
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val mNeighbourAvatar: ImageView
        val mNeighbourName: TextView
        val mDeleteButton: ImageButton

        init {
            // Enable click on item
            mNeighbourAvatar = view.findViewById(R.id.item_list_avatar)
            mNeighbourName = view.findViewById(R.id.item_list_name)
            mDeleteButton = view.findViewById(R.id.item_list_delete_button)
        }
    }
}
