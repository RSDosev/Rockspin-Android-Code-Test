package com.rockspin.androiddevtest.home

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rockspin.androiddevtest.R
import com.rockspin.androiddevtest.data.entities.CosmonautActivity
import kotlinx.android.synthetic.main.list_item_ev.view.*

class EVActivityAdapter : RecyclerView.Adapter<EVActivityAdapter.ViewHolder>() {
    var items: List<CosmonautActivity> = emptyList()
        set(newItems) {
            handleItemsUpdate(field, newItems)
            field = newItems
        }

    private fun handleItemsUpdate(oldItems: List<CosmonautActivity>, newItems: List<CosmonautActivity>) {
        if (oldItems.isEmpty()) {
            notifyDataSetChanged()
            return
        }

        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldItems.size

            override fun getNewListSize(): Int = newItems.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldItems[oldItemPosition] == newItems[newItemPosition]

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    oldItems[oldItemPosition] == newItems[newItemPosition]
        }
        ).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_ev, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(activity: CosmonautActivity) {
            itemView.purpose.text = activity.purpose
            itemView.date.text = activity.date.toString()
        }
    }
}
