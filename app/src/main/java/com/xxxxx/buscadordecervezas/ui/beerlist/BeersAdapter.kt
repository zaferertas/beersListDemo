package com.xxxxx.buscadordecervezas.ui.beerlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.xxxxx.buscadordecervezas.data.Beer
import com.xxxxx.buscadordecervezas.databinding.ListItemBinding

class BeersAdapter(val clickListener: ItemClickListener) :
    RecyclerView.Adapter<BeersAdapter.ItemViewHolder>() {

    private val items: ArrayList<Beer> = arrayListOf()

    fun setItems(newItems: List<Beer>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)
        return ItemViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    inner class ItemViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Beer) {
            binding.itemName.text = item.name
            binding.itemTagline.text = item.tagline
            binding.itemAbv.text = item.abv.toString()
            // binding.item_date.text = formatDate(beer.firstBrewed, true)
            if (item.imageUrl != null) {
                Glide.with(itemView.context).load(item.imageUrl)
                    .into(binding.itemImage)
            }
            binding.root.setOnClickListener {
                clickListener.onClick(item)
            }
        }
    }

    interface ItemClickListener {
        fun onClick(item: Beer)
    }
}