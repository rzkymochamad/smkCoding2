package com.example.challenge2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge2.DataClass.Covid19
import com.example.challenge2.DataClass.TipsItem
import com.example.challenge2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tips_item.*

class TipsAdapter(private val context: Context, private val items: List<TipsItem>, private val listener: (TipsItem)->Unit): RecyclerView.Adapter<TipsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context,
            LayoutInflater.from(context).inflate(
                R.layout.tips_item,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: TipsItem, listener: (TipsItem) -> Unit){
            txtJudul.text = item.judul
            txtDesc.text = item.deskripsi

            Glide.with(context).load(item.gambar).into(imgTips)
        }
    }
}