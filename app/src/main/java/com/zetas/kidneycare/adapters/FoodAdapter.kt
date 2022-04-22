package com.zetas.kidneycare.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zetas.kidneycare.R
import com.zetas.kidneycare.models.FoodItem

class FoodAdapter(val foodList : List<FoodItem>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.food_item_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        val imageview = holder.itemView.findViewById<ImageView>(R.id.image_foodItem)
        val tvName = holder.itemView.findViewById<TextView>(R.id.name_foodItem)
        val tvTime = holder.itemView.findViewById<TextView>(R.id.time_foodItem)
        tvTime.text = food.time
        tvName.text = food.name
        //todo : set image in imageview through Glide
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    inner class FoodViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


}