package com.example.nnaija.tikcetstopper_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_sign_in.*
import android.R.attr.data
import android.content.Context
import android.support.constraint.ConstraintSet
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.ViewUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.sign_in_tab_fragment.*
import java.util.*


class SignInActivity : MyAppCompatActivity() {
    override fun getFragmentContainerID(): Int {
        return R.id.fragmentContainer
    }


    lateinit var  googleSignInClient:GoogleSignInClient

    lateinit var vpSign:ViewPager
    val RC_SIGN_IN=100
    val TAG="SignIn Activity"




    private fun signIn() {
        val signInIntent=googleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        addBottomNavigationBar(clSign)
        val pagerAdpater: PagerAdapter
        pagerAdpater = PagerAdapter(supportFragmentManager)
        vpSign=findViewById(R.id.vpSign1)
        vpSign.adapter=pagerAdpater



        vpSign.addOnPageChangeListener(object:TabLayout.TabLayoutOnPageChangeListener(tlSign){})
        tlSign.addOnTabSelectedListener(object:TabLayout.ViewPagerOnTabSelectedListener(vpSign){})


//
//       val gso=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail().build();
//
//         googleSignInClient=GoogleSignIn.getClient(this,gso)
//        btnGoogleSignIn.setOnClickListener(this)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==RC_SIGN_IN){

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)

            updateUI(account)
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode);
            updateUI(null)
        }

    }

    private fun updateUI(account:GoogleSignInAccount?) {
        if(account!=null){
        }
    }

    class PagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                1-> MyFragment.newInstance(R.layout.register_tab_fragment)
                else ->  {
                    MyFragment.newInstance(R.layout.sign_in_tab_fragment)
                }

            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return super.getPageTitle(position)
        }
    }

      class MyFragment :Fragment(),View.OnClickListener{
          override fun onClick(v: View?) {
              if (v != null) {
                  when(v.id){
                    R.id.btnGoogleSignIn->signIn()
                  }
              }
          }

          private fun signIn() {
              val x=1
          }


          var  currentFragmentLayout:Int=R.layout.sign_in_tab_fragment
          val idBundelString="id"
          val signInFragmentId=R.layout.sign_in_tab_fragment
          val registerFragmentId=R.layout.register_tab_fragment
          companion object {


              fun newInstance(id: Int):MyFragment{

                  val fragment=MyFragment()

                  val args=Bundle()
                  args.putInt(fragment.idBundelString,id)
                  fragment.arguments=args
                  return fragment
              }
          }

          override fun onCreate(savedInstanceState: Bundle?) {
              super.onCreate(savedInstanceState)

               currentFragmentLayout=arguments?.getInt(idBundelString)?:signInFragmentId



          }

          override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
               super.onCreateView(inflater, container, savedInstanceState)
              val view=inflater.inflate(currentFragmentLayout,container,false)

              if(currentFragmentLayout === signInFragmentId){
                  val btnGoogleSignIn=view.findViewById<com.google.android.gms.common.SignInButton>(R.id.btnGoogleSignIn)
                  btnGoogleSignIn.setOnClickListener(this)
              }

              return view

          }
      }
}
