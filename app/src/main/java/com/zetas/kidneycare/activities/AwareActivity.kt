package com.zetas.kidneycare.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.zetas.kidneycare.adapters.AwareTestsAdapter
import com.zetas.kidneycare.databinding.ActivityAwareBinding
import com.zetas.kidneycare.models.AwareTestsModel

class AwareActivity : AppCompatActivity() {

    lateinit var user: FirebaseUser
    lateinit var binding: ActivityAwareBinding
    lateinit var testsAdapter: AwareTestsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //firebase user details
        user = FirebaseAuth.getInstance().currentUser!!

        //display of user details
        binding.userName.text = "Hello, ${user.displayName}"
        binding.userEmail.text = user.email
        Glide.with(this).load(user.photoUrl.toString()).into(binding.userImage)

        //closing popup acc to demand
        binding.closePopUp.setOnClickListener {
            binding.ll2.visibility = View.GONE
        }

        //DATA TO TEST LIST
        var list = ArrayList<AwareTestsModel>()
        list.add(AwareTestsModel("abc", "Rs. 6969 /-"))
        list.add(AwareTestsModel("abc", "Rs. 6969 /-"))
        list.add(AwareTestsModel("abc", "Rs. 6969 /-"))
        list.add(AwareTestsModel("abc", "Rs. 6969 /-"))

        setTestsList(list)

        //video button
        binding.v1.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=AMNlwB_CS_I"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        }

        binding.v2.setOnClickListener {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=PoZvYrvLbDU"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        }

    }

    fun setTestsList(list: ArrayList<AwareTestsModel>) {
        testsAdapter = AwareTestsAdapter(list)
        val layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.testsRv.layoutManager = layoutManager
        binding.testsRv.adapter = testsAdapter
    }
}