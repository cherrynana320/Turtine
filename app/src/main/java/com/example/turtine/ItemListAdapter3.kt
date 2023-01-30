package com.example.turtine


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.turtine.data.Item3
import com.example.turtine.databinding.ItemListItemBinding

/**
 * [ListAdapter] implementation for the recyclerview.
 */

class ItemListAdapter3(
    private val onItemClicked: (Item3) -> Unit,
    private val onTimeClicked: (Item3) -> Unit,
) :
    ListAdapter<Item3, ItemListAdapter3.ItemViewHolder3>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder3 {
        return ItemViewHolder3(
            ItemListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }


    class ItemViewHolder3( var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item3>() {
            override fun areItemsTheSame(oldItem: Item3, newItem: Item3): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item3, newItem: Item3): Boolean {
                return oldItem.itemRoutine3 == newItem.itemRoutine3
            }
        }
    }



    override fun onBindViewHolder(holder: ItemViewHolder3, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            root.setOnClickListener {
                onItemClicked(item)
            }

            imageView.setOnClickListener {
                onTimeClicked(item)
            }

            itemListRoutine.text = item.itemRoutine3
            itemListMin.text = item.itemMin3.toString() + "분"
            itemListSec.text = item.itemSec3.toString() + "초"
        }
    }


}