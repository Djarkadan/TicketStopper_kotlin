package com.example.nnaija.tikcetstopper_kotlin.module
import com.google.android.gms.auth.api.signin.GoogleSignInAccount


class GoogleUserAccount(account:GoogleSignInAccount?):UserAccount {

    private  var account:GoogleSignInAccount? = account
    override fun getUserEmail():String? {
        return account?.email
    }

    override fun getUserName():String? {
        return account?.displayName
    }

}