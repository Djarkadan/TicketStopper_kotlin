package com.example.nnaija.tikcetstopper_kotlin


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nnaija.tikcetstopper_kotlin.module.AccountProvider
import com.example.nnaija.tikcetstopper_kotlin.module.GoogleAccountProvider
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.w3c.dom.Text


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SignInFragment : Fragment(),View.OnClickListener {
    override fun onClick(v: View?) {
        if(v!=null){
            when(v.id){
                R.id.btnGoogleSignIn->{signIn(GoogleAccountProvider(context!!))}
            }
        }
    }

    private fun signIn(accountProvider: AccountProvider) {
        accountProvider.sendIntentToProvider()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view=layoutInflater.inflate(R.layout.sign_in_tab_fragment,container,false)
        val btnGoogleSign=view.findViewById<com.google.android.gms.common.SignInButton>(R.id.btnGoogleSignIn)
        btnGoogleSign.setOnClickListener(this)


        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignInFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                SignInFragment()




    }
}
