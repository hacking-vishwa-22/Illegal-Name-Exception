package com.zetas.kidneycare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zetas.kidneycare.R
import com.zetas.kidneycare.models.FoodItem

class FoodAdapter(val foodList: ArrayList<FoodItem>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.food_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.setData(
            foodList.get(position).name,
            foodList.get(position).amt,
            foodList.get(position).imageUrl
        )
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageHolder)
        val tvName: TextView = itemView.findViewById(R.id.name_foodItem)
        val tvAmt: TextView = itemView.findViewById(R.id.amt_foodItem)
        fun setData(t: String, a: String, url: String) {
            Glide.with(itemView.context).load(url).into(imageView)
            tvName.text = t
            tvAmt.text = a
        }


    }

}