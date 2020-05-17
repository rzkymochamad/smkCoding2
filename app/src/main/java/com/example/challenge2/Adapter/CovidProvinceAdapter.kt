package com.example.challenge2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2.DataClass.Attributes
import com.example.challenge2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.covid_provinsi_item.*


class CovidProvinceAdapter(private val context: Context, private val items: List<Attributes>, private val listener: (Attributes)->Unit):
    RecyclerView.Adapter<CovidProvinceAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    )= ViewHolder(context, LayoutInflater.from(context).inflate(R.layout.covid_provinsi_item, parent, false))

    override fun getItemCount():Int{
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View):RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: Attributes, listener: (Attributes) -> Unit){
            txtProvinsi.text = item.provinsi
            txtPositif.text = item.kasusPosi.toString()
            txtMeninggal.text = item.kasusMeni.toString()
            txtSembuh.text = item.kasusSemb.toString()

            containerView.setOnClickListener{listener(item)}

        }
    }
}