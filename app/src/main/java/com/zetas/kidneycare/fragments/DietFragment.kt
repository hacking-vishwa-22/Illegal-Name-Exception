package com.zetas.kidneycare.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val imageUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse4.mm.bing.net%2Fth%3Fid%3DOIP.sBKzaMaRIXxPj_eNcmqeswHaHb%26pid%3DApi&f=1"
        //todo : get list from the firebase and present it in the RV
        val foodList = listOf<FoodItem>(FoodItem("apple","2 oz",imageUrl),FoodItem("oranges","1 medium",imageUrl),FoodItem("Maar","2 jhapad",imageUrl),FoodItem("Desi Daru","90 Ml",imageUrl),FoodItem("Chakna","50gm",imageUrl))
        val adapter = FoodAdapter(foodList)
        dietRV.layoutManager = LinearLayoutManager(view.context)
        dietRV.adapter = adapter

    }
}