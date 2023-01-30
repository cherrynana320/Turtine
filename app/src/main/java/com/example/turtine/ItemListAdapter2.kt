package com.example.turtine


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.turtine.data.Item2
import com.example.turtine.databinding.ItemListItemBinding

/**
 * [ListAdapter] implementation for the recyclerview.
 */

class ItemListAdapter2(
    private val onItemClicked: (Item2) -> Unit,
    private val onTimeClicked: (Item2) -> Unit,
) :
    ListAdapter<Item2, ItemListAdapter2.ItemViewHolder2>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder2 {
        return ItemViewHolder2(
            ItemListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }




    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item2>() {
            override fun areItemsTheSame(oldItem: Item2, newItem: Item2): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item2, newItem: Item2): Boolean {
                return oldItem.itemRoutine2 == newItem.itemRoutine2
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder2, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            root.setOnClickListener {
                onItemClicked(item)
            }

            imageView.setOnClickListener {
                onTimeClicked(item)
            }

            itemListRoutine.text = item.itemRoutine2
            itemListMin.text = item.itemMin2.toString() + "분"
            itemListSec.text = item.itemSec2.toString() + "초"
        }
    }

    class ItemViewHolder2(var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}