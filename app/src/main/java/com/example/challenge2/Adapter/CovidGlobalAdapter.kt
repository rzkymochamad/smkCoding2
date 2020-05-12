package com.example.challenge2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge2.DataClass.CovidGlobalItem
import com.example.challenge2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_global_item.*

class CovidGlobalAdapter(private val context: Context, private val items:List<CovidGlobalItem>, private val listener: (CovidGlobalItem)->Unit):RecyclerView.Adapter<CovidGlobalAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ViewHolder(
        context,
        LayoutInflater.from(context).inflate(
            R.layout.covid_global_item,
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

    class ViewHolder(val context: Context, override val containerView: View):RecyclerView.ViewHolder(containerView), LayoutContainer{
        fun bindItem(item: CovidGlobalItem, listener: (CovidGlobalItem) -> Unit){
            txtCountry.text = item.combinedKey
            txtConfirmed.text = item.confirmed.toString()
            txtRecovered.text = item.recovered.toString()
            txtDeaths.text = item.deaths.toString()

            Glide.with(context).load("https://www.countryflags.io/"+item.iso2+"/flat/32.png").into(imgCountry)

            containerView.setOnClickListener{listener(item)}
        }
    }
}