package com.example.nnaija.tikcetstopper_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class HomeActivity : MyAppCompatActivity() {
    override fun getResourceLayoutId(): Int {
        return R.id.clHome
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun onBtnAlertClicked(view: View){
        Toast.makeText(this,"ss",Toast.LENGTH_LONG).show();
    }
    fun onBtnLogtClicked(view: View){
        val intent=Intent(this,SignInActivity::class.java);
        startActivity(intent);
    }




}
