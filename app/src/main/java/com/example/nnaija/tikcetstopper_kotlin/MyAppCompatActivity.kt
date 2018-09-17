package com.example.nnaija.tikcetstopper_kotlin

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.example.nnaija.tikcetstopper_kotlin.R
import kotlinx.android.synthetic.main.activity_sign_in.*

open abstract class MyAppCompatActivity:AppCompatActivity() {


    fun addBottomNavigationBar(){
        val layoutID:Int=getResourceLayoutId()

        val bottomNavigation: BottomNavigationView = BottomNavigationView(this)
        bottomNavigation.inflateMenu(R.menu.bottom_bar_menu)
        bottomNavigation.id= View.generateViewId()
        val vg: ViewGroup =findViewById(layoutID)
        val layoutparams: ViewGroup.LayoutParams= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        vg.addView(bottomNavigation, ViewGroup.LayoutParams(layoutparams))
        var constraint: ConstraintSet = ConstraintSet()
        constraint.clone(clSign)
        constraint.connect(bottomNavigation.id, ConstraintSet.BOTTOM,clSign.id, ConstraintSet.BOTTOM)
        constraint.applyTo(clSign)


    }

    abstract fun getResourceLayoutId():Int
}