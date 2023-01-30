package com.example.turtine


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.turtine.data.Item1
import com.example.turtine.databinding.ItemListItemBinding

/**
 * [ListAdapter] implementation for the recyclerview.
 */

class ItemListAdapter1(
    private val onItemClicked: (Item1) -> Unit,
    private val onTimeClicked: (Item1) -> Unit,
) :
    ListAdapter<Item1, ItemListAdapter1.ItemViewHolder1>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder1 {
        return ItemViewHolder1(
            ItemListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder1, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            root.setOnClickListener {
                onItemClicked(item)
            }

            imageView.setOnClickListener {
                onTimeClicked(item)
            }

            itemListRoutine.text = item.itemRoutine1
            itemListMin.text = item.itemMin1.toString() + "분"
            itemListSec.text = item.itemSec1.toString() + "초"
        }
    }

    class ItemViewHolder1( var binding: ItemListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        }


    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item1>() {
            override fun areItemsTheSame(oldItem: Item1, newItem: Item1): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item1, newItem: Item1): Boolean {
                return oldItem.itemRoutine1 == newItem.itemRoutine1
            }
        }
    }
}