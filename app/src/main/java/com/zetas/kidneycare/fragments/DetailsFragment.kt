package com.zetas.kidneycare.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.zetas.kidneycare.R
import java.math.RoundingMode.valueOf

class DetailsFragment : Fragment() {

    lateinit var btnSubmit:AppCompatButton
    lateinit var veg_nonveg_switch:Switch
    lateinit var ageEt:EditText
    lateinit var btn0: RadioButton
    lateinit var btn00: RadioButton
    lateinit var btn1: RadioButton
    lateinit var btn2: RadioButton
    lateinit var btn3: RadioButton
    lateinit var btn4: RadioButton
    lateinit var btn5: RadioButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("####","Loaded detailsFragment")
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        veg_nonveg_switch = view.findViewById(R.id.veg_nonveg_switch)
        ageEt = view.findViewById(R.id.ageEt)
        btnSubmit = view.findViewById(R.id.btnSubmit)
        btn1 = view.findViewById(R.id.btn1)
        btn2 = view.findViewById(R.id.btn2)
        btn3 = view.findViewById(R.id.btn3)
        btn4 = view.findViewById(R.id.btn4)
        btn5 = view.findViewById(R.id.btn5)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(checkAllOkay())
            btnSubmit.isEnabled = true
    }

    private fun checkAllOkay(): Boolean {
        if(btn1.isChecked || btn2.isChecked || btn3.isChecked || btn4.isChecked || btn5.isChecked){
            val age = (ageEt.text.toString()).toInt()
            if(age < 10 || age > 100){
                Toast.makeText(context,"Age not allowed",Toast.LENGTH_SHORT).show()
                return false
            }
            else{
                return false
            }
        }else {
            return false
        }
    }

}