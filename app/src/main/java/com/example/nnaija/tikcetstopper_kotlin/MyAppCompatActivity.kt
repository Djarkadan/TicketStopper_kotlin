package com.example.nnaija.tikcetstopper_kotlin

import android.app.Fragment
import android.app.FragmentTransaction
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.example.nnaija.tikcetstopper_kotlin.R
import kotlinx.android.synthetic.main.activity_sign_in.*

open abstract class MyAppCompatActivity:AppCompatActivity() {


    fun addBottomNavigationBar(constraintLayout:ConstraintLayout){
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bnb)

        bottomNavigation.setOnNavigationItemSelectedListener{item: MenuItem -> onBottomBtnNavigationPressed(item) }
    }

    private fun onBottomBtnNavigationPressed(item: MenuItem): Boolean {
        var x:String=""
        val id=item.itemId
        when(item.itemId){
            R.id.bottombaritem_alert->{
                val alertFragment:AlertFragment=AlertFragment.newInstance()
                launchFragment(alertFragment)
            }
            R.id.bottombaritem_account->{
                val accountFragment:AcountLogInRegisterFragment=AcountLogInRegisterFragment.newInstance()
                launchFragment(accountFragment)
            }
            R.id.bottombaritem_valet->x=R.id.bottombaritem_valet.toString()
            R.id.bottombaritem_parking->x=R.id.bottombaritem_parking.toString()
        }



        return true
    }

    private fun launchFragment(fragment:android.support.v4.app.Fragment){
        val activityFragmentContainerID:Int=getFragmentContainerID()
        val transaction: android.support.v4.app.FragmentTransaction =supportFragmentManager.beginTransaction()
        transaction.replace(activityFragmentContainerID,fragment)
        transaction.addToBackStack(null)
        transaction.commit()





    }
    abstract fun getFragmentContainerID():Int
}