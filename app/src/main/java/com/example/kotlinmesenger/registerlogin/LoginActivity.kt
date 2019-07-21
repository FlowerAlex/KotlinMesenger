package com.example.kotlinmesenger.registerlogin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinmesenger.R
import com.example.kotlinmesenger.messages.LatestMessagesActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener(){

            val email : String = email_edittext_login.text.toString()
            val password : String = password_edittext_login.text.toString()

            Log.d("Login","Attempt login with email/pw: $email /...")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task->
                    if (task.isSuccessful){
                        val intent = Intent(this, LatestMessagesActivity::class.java)

                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }else{
                        Log.d("LoginActivity","signInWithEmail:failure")
                    }
                }
        }


        back_to_register_text_view.setOnClickListener {
            finish()
        }
    }

}