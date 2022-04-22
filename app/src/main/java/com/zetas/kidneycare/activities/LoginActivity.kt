package com.zetas.kidneycare.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.zetas.kidneycare.MainActivity
import com.zetas.kidneycare.R
import com.zetas.kidneycare.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding
    lateinit var user: FirebaseUser
    private var googleClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

       //init firebase auth
        auth = Firebase.auth



        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("342300055506-tofis9smtni6mfit32gkh66q1lnjqbh7.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleClient = GoogleSignIn.getClient(this, gso)

        binding.googleSignIn.setOnClickListener {
            signIn()
        }


    }

    private fun signIn() {
        if(googleClient!=null){
            val signInIntent: Intent = googleClient!!
                .getSignInIntent()
            startActivityForResult(signInIntent, 6969)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //handling success and errors
        if (requestCode == 6969) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account:GoogleSignInAccount = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account.idToken!!)


            } catch (e: ApiException) {
                Toast.makeText(this@LoginActivity, "thing Went Wrong!", Toast.LENGTH_LONG)
                    .show()
                googleClient?.signOut()
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Haha", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //signIN Failed
                    Toast.makeText(
                        this@LoginActivity,
                        "Something Went Wrong!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}