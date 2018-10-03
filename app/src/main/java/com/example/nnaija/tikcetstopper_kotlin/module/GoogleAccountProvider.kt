package com.example.nnaija.tikcetstopper_kotlin.module

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import android.R.attr.data
import android.util.Log
import com.google.android.gms.common.api.ApiException


class GoogleAccountProvider(context:Context,accountAskerListener:AccountProvider.onAccountResultReceivedListner): AppCompatActivity(),AccountProvider {


    private lateinit var  googleSignInClient: GoogleSignInClient
    private lateinit var gso:GoogleSignInOptions
    private val RC_SIGN_IN_REQUEST_CODE=100
    private lateinit var context:Context
    private  var fManager:FragmentManager?
    private   var account: GoogleSignInAccount? = null
    private  var listener:AccountProvider.onAccountResultReceivedListner?=null

    override fun sendIntentToProvider() {

        val intent:Intent=googleSignInClient.signInIntent
//        val activity=context as? Activity
//        activity?.startActivityForResult(intent,RC_SIGN_IN_REQUEST_CODE)

        val fragment:Fragment= @SuppressLint("ValidFragment")
        object:Fragment(){
            override fun onAttach(context: Context?) {
                super.onAttach(context)
                startActivityForResult(intent,RC_SIGN_IN_REQUEST_CODE)
            }

            override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)

                if(requestCode==RC_SIGN_IN_REQUEST_CODE){
                    val task=GoogleSignIn.getSignedInAccountFromIntent(intent)
                    handleSignInResult(task)
                }

                val fragment=fManager?.findFragmentByTag("test")
                if(fragment!=null){
                    fManager?.beginTransaction()?.remove(fragment)?.commit()
                }
            }

        }

        fManager?.beginTransaction()?.add(fragment,"test")?.commit()

    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try {
            val account:GoogleSignInAccount?=task?.getResult(ApiException::class.java)
                this.account=account
                listener?.onAccountReceived(account!!)


        }catch (e:ApiException){
            Log.w("s", "signInResult:failed code=" + e.statusCode)
        }
    }


    override fun getAccount() {
    }


    init {
        this.context=context
        fManager=(context as? Activity)?.fragmentManager
        gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        googleSignInClient=GoogleSignIn.getClient(context,gso)
        listener=accountAskerListener

    }




}