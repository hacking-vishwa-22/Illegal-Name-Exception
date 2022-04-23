package com.zetas.kidneycare.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding
    lateinit var stage: String
    lateinit var age: String
    var foodPref: String="v" //v for veg and nv for non-veg
    var isSurveyed: Long = 1L  //1L -> Not surveyed and 2L means surveyed
    var filledAllOptions: Int = 0
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view: View = binding.getRoot()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init firebase
        db = FirebaseFirestore.getInstance()

        //Stage Handle
        binding.Rgroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            filledAllOptions = 1
            when (checkedId) {
                R.id.btn1 -> stage = "1"
                R.id.btn2 -> stage = "2"
                R.id.btn3 -> stage = "3"
                R.id.btn4 -> stage = "4"
                R.id.btn5 -> stage = "5"
                else -> {
                    Toast.makeText(context, "Please select one Option!", Toast.LENGTH_SHORT).show()
                }
            }

        })

        //Age handling
        binding.ageEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (!s.toString().equals("")) {
                    age = binding.ageEt.text.toString()
                    filledAllOptions = 2
                } else {

                }
            }
        })


        //Food Pref Handling
        binding.vegNonvegSwitch.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                foodPref = "nv"
            } else if (!isChecked) {
                foodPref = "v"
            }
        })

        binding.btnSubmit.setOnClickListener {
            if (filledAllOptions >= 2) {

                //FIREBASE STORAGE
                isSurveyed = 2L
                var newPost: HashMap<String, Any> = HashMap()
                newPost.put("stage", stage)
                newPost.put("age", age)
                newPost.put("foodPref", foodPref)
                newPost.put("isSurveyed", isSurveyed)

                db.collection("UserDetails")
                    .document(FirebaseAuth.getInstance().currentUser?.email.toString()).set(newPost)
                    .addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful) {
                                changeFragment(DashboardFragment())
                            } else {
                                Toast.makeText(
                                    context,
                                    "Something Went Wrong! " + it.exception?.message,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        })

                changeFragment(DashboardFragment())


            } else {
                Toast.makeText(context, "Please fill all the options!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.apply {
            setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
            replace(R.id.frameLayoutSurvey, fragment)
            commit()
        }
    }
}