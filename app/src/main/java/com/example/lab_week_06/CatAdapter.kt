package com.example.lab_week_06

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    private val cats = mutableListOf<CatModel>()

    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        // PERBAIKAN 1: Panggil konstruktor dengan 2 argumen saja
        return CatViewHolder(view, imageLoader)
    }

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val catItem = cats[position]
        holder.bindData(catItem)

        // PERBAIKAN 2: Logika klik diterapkan di sini
        holder.itemView.setOnClickListener {
            onClickListener.onItemClick(catItem)
        }
    }

    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }
}