package com.example.nnaija.tikcetstopper_kotlin

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_sign_in.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AcountLogInRegisterFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AcountLogInRegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AcountLogInRegisterFragment : Fragment() {


    lateinit var vpSign: ViewPager
    lateinit var pagerAdpater:PagerAdapter
    val RC_SIGN_IN=100
    val TAG="SignIn Activity"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view:View=inflater.inflate(R.layout.fragment_acount_log_in_register, container, false)
        vpSign=view.findViewById(R.id.vpSignInFragment)
        pagerAdpater = PagerAdapter(childFragmentManager)
        vpSign.adapter=pagerAdpater
        vpSign.addOnPageChangeListener(object: TabLayout.TabLayoutOnPageChangeListener(tlSign){})
        val tlSign1:TabLayout=view.findViewById(R.id.tlSign)
        tlSign1.addOnTabSelectedListener(object: TabLayout.ViewPagerOnTabSelectedListener(vpSign){})

        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {

        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }



    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AcountLogInRegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AcountLogInRegisterFragment()




    }

    class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when(position){
                1-> SignInActivity.MyFragment.newInstance(R.layout.register_tab_fragment)
                else ->  {
                    SignInActivity.MyFragment.newInstance(R.layout.sign_in_tab_fragment)
                }

            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return super.getPageTitle(position)
        }
    }
    class MyFragment :Fragment(){
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

            return view

        }
    }
}
