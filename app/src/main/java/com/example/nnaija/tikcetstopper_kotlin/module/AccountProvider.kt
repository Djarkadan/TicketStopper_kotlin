package com.example.nnaija.tikcetstopper_kotlin.module


interface AccountProvider {
    fun askForAccountToProvider()
    interface onAccountResultReceivedListner{
        fun onAccountReceived(account:UserAccount)
    }
}