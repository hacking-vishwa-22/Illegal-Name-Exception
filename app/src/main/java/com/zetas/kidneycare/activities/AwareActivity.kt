package com.zetas.kidneycare.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.zetas.kidneycare.adapters.AwareTestsAdapter
import com.zetas.kidneycare.databinding.ActivityAwareBinding
import com.zetas.kidneycare.models.AwareTestsModel

class AwareActivity : AppCompatActivity() {

    lateinit var user: FirebaseUser
    lateinit var binding: ActivityAwareBinding
    lateinit var testsAdapter: AwareTestsAdapter
    lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAwareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //forcing light theme


        //firebase user details
        user = FirebaseAuth.getInstance().currentUser!!
        db = FirebaseFirestore.getInstance()

        db.collection("UserDetails").document(user.email.toString()).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val userIsSurveyed = it.result.get("isSurveyed")
                if (userIsSurveyed == 1L) {
                    binding.goToSurvey.text = "Take the survey"
                } else {
                    binding.goToSurvey.text = "My Dashboard"
                }
            }

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
            list.add(AwareTestsModel("Blood pressure Checkup", "Rs. 500 /-"))
            list.add(AwareTestsModel("Urine Test for Albumin", "Rs. 400 /-"))
            list.add(AwareTestsModel("Blood test for GFR", "Rs. 900 /-"))
            list.add(AwareTestsModel("Kidney ultrasound", "Rs. 1200 /-"))

            setTestsList(list)

            //video button
            binding.v1.setOnClickListener {
                val intent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=AMNlwB_CS_I")
                    )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.google.android.youtube")
                startActivity(intent)
            }

            binding.v2.setOnClickListener {
                val intent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=PoZvYrvLbDU")
                    )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.google.android.youtube")
                startActivity(intent)
            }

            binding.goToSurvey.setOnClickListener {

                db.collection("UserDetails").document(user.email.toString()).get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val userIsSurveyed = it.result.get("isSurveyed")
                            if (userIsSurveyed == 1L) {
                                val goIntent =
                                    Intent(this@AwareActivity, SurveyActivity::class.java)
                                goIntent.putExtra("checker", "NO")
                                startActivity(goIntent)
                            } else {
                                val goIntent =
                                    Intent(this@AwareActivity, SurveyActivity::class.java)
                                goIntent.putExtra("checker", "YES")
                                startActivity(goIntent)
                            }


                        } else {
                            Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
                        }
                    }


            }

            binding.logout.setOnClickListener {

                FirebaseAuth.getInstance().signOut()
                val out = Intent(this@AwareActivity, LoginActivity::class.java)
                startActivity(out)
                finish()
            }

        }


    }
    fun setTestsList(list: ArrayList<AwareTestsModel>) {
        testsAdapter = AwareTestsAdapter(list)
        val layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.testsRv.layoutManager = layoutManager
        binding.testsRv.adapter = testsAdapter
    }
}