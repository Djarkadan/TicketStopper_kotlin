package com.example.nnaija.tikcetstopper_kotlin


import android.net.Uri
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.activity_home.*







class HomeActivity : MyAppCompatActivity(),
        HomeFragment.OnFragmentInteractionListener ,
        AcountLogInRegisterFragment.OnFragmentInteractionListener,
        AlertFragment.OnFragmentInteractionListener{



    override fun getFragmentContainerID(): Int {
        return R.id.fragmentContainer
    }

    override fun onFragmentInteraction(uri: Uri) {
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


}
