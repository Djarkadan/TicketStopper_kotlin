package com.example.nnaija.tikcetstopper_kotlin

import android.app.FragmentTransaction
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : MyAppCompatActivity(),HomeFragment.OnFragmentInteractionListener ,AcountLogInRegisterFragment.OnFragmentInteractionListener{
    override fun getFragmentContainerID(): Int {
        return R.id.fragmentContainer
    }

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun getResourceLayoutId(): Int {
        return R.id.clHome
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.addBottomNavigationBar(clHome)

        if(R.id.fragmentContainer!=null){
            if(savedInstanceState!=null){
                return
            }

            val homeFragment:HomeFragment=HomeFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragmentContainer,homeFragment).commit()


        }




    }

//    fun onBtnAlertClicked(view: View){
//        Toast.makeText(this,"ss",Toast.LENGTH_LONG).show();
//    }
//    fun onBtnLogtClicked(view: View){
//        val intent=Intent(this,SignInActivity::class.java);
//        startActivity(intent);
//    }




}
