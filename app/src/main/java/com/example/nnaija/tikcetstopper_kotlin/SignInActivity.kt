package com.example.nnaija.tikcetstopper_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_sign_in.*
import android.R.attr.data
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.support.v4.app.FragmentActivity
import android.util.Log


class SignInActivity : AppCompatActivity(),View.OnClickListener {

    lateinit var  googleSignInClient:GoogleSignInClient
    val RC_SIGN_IN=100
    val TAG="SignIn Activity"


    override fun onClick(v: View) {

        when(v.id){
            R.id.btnGoogleSignIn->signIn()
        }

    }

    private fun signIn() {
        val signInIntent=googleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

       val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

         googleSignInClient=GoogleSignIn.getClient(this,gso)
        btnGoogleSignIn.setOnClickListener(this);
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==RC_SIGN_IN){

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            updateUI(account)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode);
            updateUI(null)
        }

    }

    private fun updateUI(account:GoogleSignInAccount?) {
        if(account!=null){
        }
    }
}
