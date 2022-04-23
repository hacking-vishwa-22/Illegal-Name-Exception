package com.zetas.kidneycare.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.razorpay.Checkout
import com.zetas.kidneycare.databinding.ActivityPaymentBinding
import org.json.JSONObject

class PaymentActivity : AppCompatActivity() {
    var totalAmt: String? = "0"
    lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        totalAmt = intent.getStringExtra("amt")


        binding.pay.setOnClickListener {
            if (!totalAmt.equals("0")) {
                startPayment()
            } else {
                Toast.makeText(this, "Payment cannot be processed right now!", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    //payment block starts
    private fun startPayment() {

        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("rzp_test_DGEB5RtLHDtoiB")

        try {
            val options = JSONObject()
            options.put("name", "Tests by KidneyCare")
            options.put("description", "Buy our tests to get a better healthy life")
            options.put("send_sms_hash", true)
            options.put("allow_rotation", true)
            options.put("currency", "INR")
            options.put("amount", totalAmt + "00")

            val preFill = JSONObject()
            options.put("email", FirebaseAuth.getInstance().currentUser?.email)
            options.put("contact", FirebaseAuth.getInstance().currentUser?.phoneNumber)


            options.put("prefill", preFill)

            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }
}