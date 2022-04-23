package com.zetas.kidneycare.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
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

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //forcing light theme


        //init firebase auth
        auth = FirebaseAuth.getInstance()


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
        if (googleClient != null) {
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
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)


            } catch (e: ApiException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Something Went Wrong!",
                    Toast.LENGTH_LONG
                ).show()
                googleClient?.signOut()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener {
                if (it.isSuccessful) {
                    user = auth.currentUser!!
                    val userPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE)
                    val editor = userPreferences.edit()
                    editor.putString("USER_ID", user.uid)
                    Log.d("####", "${user.displayName} \n ${user.email}")
                    editor.putString("USER_NAME", user.displayName)
                    editor.putString("USER_EMAIL", user.email)
                    editor.putString("USER_PHONE", user.phoneNumber)
                    editor.putString("USER_PROFILE", user.photoUrl.toString())
                    //Toast.makeText(this, "All okay. Nice", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, AwareActivity::class.java)
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