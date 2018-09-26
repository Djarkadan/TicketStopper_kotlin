package com.example.nnaija.tikcetstopper_kotlin.module

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GoogleAccountProvider(context:Context):AccountProvider {


    private lateinit var  googleSignInClient: GoogleSignInClient
    private lateinit var gso:GoogleSignInOptions
    private val RC_SIGN_IN_REQUEST_CODE=100
    private lateinit var context:Context


    override fun sendIntentToProvider() {
        val intent:Intent=googleSignInClient.signInIntent
        val activity=context as? Activity
        activity?.startActivityForResult(intent,RC_SIGN_IN_REQUEST_CODE)

    }

    override fun getAccount() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        this.context=context
        gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient=GoogleSignIn.getClient(context,gso)


    }

    inner class acti():Activity(){
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            val x=1
        }
    }

}