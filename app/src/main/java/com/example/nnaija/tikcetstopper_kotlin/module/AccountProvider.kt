package com.example.nnaija.tikcetstopper_kotlin.module

import android.content.Intent

interface AccountProvider {
    fun getAccount()
    fun sendIntentToProvider()
    interface onAccountResultReceivedListner{
        fun onAccountReceived(account:Any)
    }
}