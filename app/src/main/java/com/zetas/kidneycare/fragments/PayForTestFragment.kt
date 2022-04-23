package com.zetas.kidneycare.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zetas.kidneycare.activities.PaymentActivity
import com.zetas.kidneycare.databinding.FragmentPayForTestBinding

class PayForTestFragment : Fragment() {
    lateinit var binding: FragmentPayForTestBinding
    var c1: Boolean = false
    var c2: Boolean = false
    var c3: Boolean = false
    var c4: Boolean = false

    var t1: Int = 500
    var t2: Int = 400
    var t3: Int = 200
    var t4: Int = 900
    var totalAmt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPayForTestBinding.inflate(inflater, container, false)
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goTest1.setOnClickListener {
            if (c1) {
                c1 = false
                binding.goTest1.setBackgroundColor(Color.GRAY)
                totalAmt = totalAmt - t1
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            } else {
                c1 = true
                binding.goTest1.setBackgroundColor(Color.GREEN)
                totalAmt = t1 + totalAmt
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            }
        }


        binding.goTest2.setOnClickListener {
            if (c2) {

                c2 = false
                binding.goTest2.setBackgroundColor(Color.GRAY)
                totalAmt = totalAmt - t2
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            } else {

                c2 = true
                binding.goTest2.setBackgroundColor(Color.GREEN)
                totalAmt = t2 + totalAmt
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            }

        }

        binding.goTest3.setOnClickListener {
            if (c3) {

                c3 = false
                binding.goTest3.setBackgroundColor(Color.GRAY)
                totalAmt = totalAmt - t3
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            } else {

                c3 = true
                binding.goTest3.setBackgroundColor(Color.GREEN)
                totalAmt = t3 + totalAmt
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            }


        }

        binding.goTest4.setOnClickListener {
            if (c4) {

                c4 = false
                binding.goTest4.setBackgroundColor(Color.GRAY)
                totalAmt = totalAmt - t4
                binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"

            } else {
                binding.goTest4.setOnClickListener {
                    c4 = true
                    binding.goTest4.setBackgroundColor(Color.GREEN)
                    totalAmt = t4 + totalAmt
                    binding.totalPrice.text = "Rs. ${totalAmt.toString()} /-"
                }
            }
        }











        binding.payBtn.setOnClickListener {
            if (totalAmt == 0) {
                Toast.makeText(context, "Please select to proceed!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(context, PaymentActivity::class.java)
                intent.putExtra("amt", totalAmt.toString())
                startActivity(intent)
            }

        }

    }

}

