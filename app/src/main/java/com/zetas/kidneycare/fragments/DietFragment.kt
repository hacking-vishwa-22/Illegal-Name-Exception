package com.zetas.kidneycare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.zetas.kidneycare.R
import com.zetas.kidneycare.adapters.FoodAdapter
import com.zetas.kidneycare.databinding.FragmentDietBinding
import com.zetas.kidneycare.models.FoodItem


class DietFragment : Fragment() {
    lateinit var binding: FragmentDietBinding
    lateinit var db: FirebaseFirestore
    lateinit var dietRV: RecyclerView
    var list = ArrayList<FoodItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietBinding.inflate(inflater,container,false)
        val view = binding.root
        dietRV = binding.dietRV
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        val imageUrl = "https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"
        //todo : get list from the firebase and present it in the RV
       list.add(FoodItem("Apple","1 piece/day","https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"))
       list.add(FoodItem("Apple","1 piece/day","https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"))
       list.add(FoodItem("Apple","1 piece/day","https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"))
       list.add(FoodItem("Apple","1 piece/day","https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"))
       list.add(FoodItem("Apple","1 piece/day","https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"))
       list.add(FoodItem("Apple","1 piece/day","https://images.news18.com/ibnlive/uploads/2021/01/1610716314_untitled-design-2021-01-15t184025.049.jpg"))
        val adapter = FoodAdapter(list)
        dietRV.layoutManager = GridLayoutManager(view.context,3)
        dietRV.adapter = adapter

    }
}