package com.zetas.kidneycare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zetas.kidneycare.R
import com.zetas.kidneycare.models.AwareTestsModel

class AwareTestsAdapter(val list: ArrayList<AwareTestsModel>) :
    RecyclerView.Adapter<AwareTestsAdapter.AwareViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AwareViewHolder {
        return AwareViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.test_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AwareViewHolder, position: Int) {
        holder.setData(list.get(position).testTitle, list.get(position).price)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class AwareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTv: TextView = itemView.findViewById(R.id.test_title)
        val priceTv: TextView = itemView.findViewById(R.id.test_price)

        fun setData(t: String, p: String) {
            titleTv.text = t
            priceTv.text = p
        }
    }


}