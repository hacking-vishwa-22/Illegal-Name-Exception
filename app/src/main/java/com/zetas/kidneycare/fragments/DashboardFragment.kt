package com.zetas.kidneycare.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.zetas.kidneycare.R
import com.zetas.kidneycare.activities.LoginActivity
import com.zetas.kidneycare.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    lateinit var user: FirebaseUser
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting and setting user
        user = FirebaseAuth.getInstance().currentUser!!
        db = FirebaseFirestore.getInstance()

        db.collection("UserDetails").document(user.email.toString()).get().addOnCompleteListener {
            if (it.isSuccessful) {
                val userDetails = it.result
                binding.userStage.text = "Stage: ${userDetails.get("stage").toString()}"
                binding.userAge.text = "Age: ${userDetails.get("age").toString()}"
                when (userDetails.get("stage").toString()) {
                    "1" -> binding.userStatus.text =
                        "The damage to your kidneys is mild. Your kidneys are still working well, but you may have signs of kidney damage or physical damage to your kidneys. \n" +
                                "\n" +
                                "Stage 1 CKD means you have a normal estimated glomerular filtration rate (eGFR) of 90 or greater, but there is protein in your urine (i.e., your pee). The presence of protein alone means you are in Stage 1 CKD."
                    "2" -> binding.userStatus.text =
                        " Your eGFR has gone down to between 60 and 89. However, your kidneys are mostly still able to work as they should to filter your blood, which is why you may not notice any effects on your health. While the damage to your kidneys may not be reversible, there is a lot you can do to slow down the damage to your kidneys. You may or may not also have protein in your urine."
                    "3" -> binding.userStatus.text =
                        "Your kidneys have mild to moderate damage, and they are less able to filter waste and fluid out of your blood. This waste can build up in your body and begin to harm other areas, such as to cause high blood pressure, anemia and problems with your bones. This buildup of waste is called uremia.\n" +
                                "\n" +
                                "Stage 3 CKD is split into 2 sub-stages based on your eGFR:\n" +
                                "\n" +
                                "Stage 3a means you have an eGFR between 45 and 59, and Stage 3b means you have an eGFR between 30 and 44."
                    "4" -> binding.userStatus.text =
                        "Your kidneys are moderately or severely damaged and are not working as well as they should to filter waste from your blood. Waste products may build up in your blood and cause other health problems, such as:\n" +
                                "\n" +
                                "High blood pressure\n" +
                                "Anemia (not enough red blood cells in your body) \n" +
                                "Bone disease\n" +
                                "Heart disease\n" +
                                "High potassium\n" +
                                "High phosphorus \n" +
                                "Metabolic acidosis (a buildup of acid in your body)"
                    "5" -> binding.userStatus.text =
                        "Your kidneys are severely damaged and have stopped doing their job to filter waste from your blood. Waste products may build up in your blood and cause other health problems, such as:\n" +
                                "\n" +
                                "High blood pressure\n" +
                                "Anemia (not enough red blood cells in your body) \n" +
                                "Bone disease\n" +
                                "Heart disease\n" +
                                "High potassium \n" +
                                "High phosphorus \n" +
                                "Metabolic acidosis (a buildup of acid in your body)"
                    else -> binding.userStatus.text = "No Data Found. Maybe some internet issue"
                }

            } else {
                Toast.makeText(
                    context,
                    "Something Went Wrong! Check your internet connection!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.userName.text = "${user.displayName}\'s Dashboard"


        //Buttons
        binding.goTest.setOnClickListener {
            changeFragment(PayForTestFragment())
        }
        binding.goDiet.setOnClickListener {

        }
        binding.goCenters.setOnClickListener {

        }
        binding.goLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val out = Intent(context, LoginActivity::class.java)
            startActivity(out)
            activity?.finish()
        }

        binding.goOneMoreSurvey.setOnClickListener {
            changeFragment(DetailsFragment())
        }


    }

    private fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.apply {
            replace(R.id.frameLayoutSurvey, fragment)
            commit()
        }
    }
}
