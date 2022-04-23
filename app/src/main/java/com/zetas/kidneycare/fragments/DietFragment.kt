package com.zetas.kidneycare.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.zetas.kidneycare.adapters.FoodAdapter
import com.zetas.kidneycare.databinding.FragmentDietBinding
import com.zetas.kidneycare.models.FoodItem


class DietFragment : Fragment() {
    lateinit var binding: FragmentDietBinding
    lateinit var db: FirebaseFirestore
    lateinit var dietRV: RecyclerView
    var list = ArrayList<FoodItem>()
    lateinit var veg: String
    lateinit var  adapter:FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDietBinding.inflate(inflater, container, false)
        val view = binding.root
        dietRV = binding.dietRV
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()

        adapter = FoodAdapter(list)

        db.collection("Diets").document("1").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val ob = it.result
                list.add(
                    FoodItem(
                        ob.get("name").toString(),
                        ob.get("amt").toString(),
                        ob.get("image").toString()
                    )
                )
                val adapter = FoodAdapter(list)
                dietRV.layoutManager = GridLayoutManager(view.context, 3)
                dietRV.adapter = adapter
            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong with the Network!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        db.collection("Diets").document("2").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val ob = it.result
                list.add(
                    FoodItem(
                        ob.get("name").toString(),
                        ob.get("amt").toString(),
                        ob.get("image").toString()
                    )
                )
                val adapter = FoodAdapter(list)
                dietRV.layoutManager = GridLayoutManager(view.context, 3)
                dietRV.adapter = adapter
            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong with the Network!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        db.collection("Diets").document("3").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val ob = it.result
                list.add(
                    FoodItem(
                        ob.get("name").toString(),
                        ob.get("amt").toString(),
                        ob.get("image").toString()
                    )
                )
                val adapter = FoodAdapter(list)
                dietRV.layoutManager = GridLayoutManager(view.context, 3)
                dietRV.adapter = adapter
            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong with the Network!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        db.collection("Diets").document("4").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val ob = it.result
                list.add(
                    FoodItem(
                        ob.get("name").toString(),
                        ob.get("amt").toString(),
                        ob.get("image").toString()
                    )
                )
                val adapter = FoodAdapter(list)
                dietRV.layoutManager = GridLayoutManager(view.context, 3)
                dietRV.adapter = adapter
            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong with the Network!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        db.collection("Diets").document("5").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val ob = it.result
                list.add(
                    FoodItem(
                        ob.get("name").toString(),
                        ob.get("amt").toString(),
                        ob.get("image").toString()
                    )
                )
                val adapter = FoodAdapter(list)
                dietRV.layoutManager = GridLayoutManager(view.context, 3)
                dietRV.adapter = adapter
            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong with the Network!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        db.collection("Diets").document("6").get().addOnCompleteListener {
            if (it.isSuccessful) {
                val ob = it.result
                list.add(
                    FoodItem(
                        ob.get("name").toString(),
                        ob.get("amt").toString(),
                        ob.get("image").toString()
                    )
                )
                val adapter = FoodAdapter(list)
                dietRV.layoutManager = GridLayoutManager(view.context, 3)
                dietRV.adapter = adapter

                binding.load.visibility = View.GONE


            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong with the Network!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        db.collection("UserDetails")
            .document(FirebaseAuth.getInstance().currentUser?.email.toString()).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    veg = it.result.get("foodPref").toString()
                } else {
                    Toast.makeText(
                        context,
                        "Something Went Wrong with the Network!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        veg = "v" //default

        if (veg.equals("v")) {

            list.add(
                FoodItem(
                    "Cooked Beans",
                    "1 cup",
                    "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/delish-200318-seo-how-to-cook-beans-horizontal-final-14288-eb-1585337558.jpg"

                )
            )
            list.add(
                FoodItem(
                    "Potatoes",
                    "1/2 cups",
                    "https://i2.wp.com/completelydelicious.com/wp-content/uploads/2020/10/buttery-boiled-potatoes-6.jpg"

                )
            )
            list.add(
                FoodItem(
                    "Cabbage",
                    "1/2 cups",
                    "https://www.spendwithpennies.com/wp-content/uploads/2020/02/Boiled-Cabbage-SpendWithPennies-3.jpg"

                )
            )
            adapter.notifyDataSetChanged()


        } else if (veg.equals("nv")) {
            list.add(
                FoodItem(
                    "Fish",
                    "1 oz",
                    "https://www.seriouseats.com/thmb/Bo264kGbS24I9-_eUcgIsRT0N3M=/2800x1575/smart/filters:no_upscale()/20211201-whole-roasted-fish-vicky-wasik-5-f0627cc547f74317b944f5a323fcc6b3.jpg"

                )
            )
            list.add(
                FoodItem(
                    "Egg",
                    "1 oz",
                    "https://static.diabetesfoodhub.org/system/thumbs/system/images/recipes/1930-hard-boiled-egg-diabetic_AS_163304407_031920_3864451747.jpg"

                )
            )
            list.add(
                FoodItem(
                    "Meat",
                    "1 oz",
                    "https://www.myactivekitchen.com/wp-content/uploads/2020/07/boiled-meat-img-11.jpg"

                )
            )
            adapter.notifyDataSetChanged()

        }

    }

}