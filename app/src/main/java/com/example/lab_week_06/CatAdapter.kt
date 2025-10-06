package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    private val cats = mutableListOf<CatModel>()
    val swipeToDeleteCallback = SwipeToDeleteCallback()


    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader)
    }

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem = cats[position]
        holder.bindData(catItem)

        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(catItem)
        }
    }

    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }

    // Inner class untuk Swipe-to-Delete
    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(
        0, // Arah 'drag' tidak diaktifkan
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT // Arah 'swipe' ke kiri dan kanan
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false // Fungsi 'move' tidak digunakan

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    } // <-- Kurung kurawal yang salah dipindahkan ke sini
}