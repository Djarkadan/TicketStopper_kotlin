package com.example.nnaija.tikcetstopper_kotlin

import android.app.Activity
import android.app.FragmentTransaction
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.nnaija.tikcetstopper_kotlin.module.AccountProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_home.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount



class HomeActivity : MyAppCompatActivity(),
        HomeFragment.OnFragmentInteractionListener ,
        AcountLogInRegisterFragment.OnFragmentInteractionListener,
        AlertFragment.OnFragmentInteractionListener,View.OnClickListener{
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.getId()) {
                R.id.sign_in_button -> signIn()
            }
        }// ...
    }

    private fun signIn() {

        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, 100)
    }

    override fun getFragmentContainerID(): Int {
        return R.id.fragmentContainer
    }

    override fun onFragmentInteraction(uri: Uri) {
    }


    private  lateinit var mGoogleSignInClient:GoogleSignInClient
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if(account!=null)mGoogleSignInClient.signOut()


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.addBottomNavigationBar(clHome)
        findViewById<com.google.android.gms.common.SignInButton>(R.id.sign_in_button).setOnClickListener(this);

        if(R.id.fragmentContainer!=null){
            if(savedInstanceState!=null){
                return
            }
            val homeFragment:HomeFragment=HomeFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,homeFragment).commit()
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }


}
