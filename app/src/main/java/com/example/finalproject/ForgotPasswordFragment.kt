package com.example.finalproject

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment: Fragment() {
    val TAG = "FragmentForgot"
     var auth: FirebaseAuth?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_forgotpassword_fragment, container, false)
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG,"onCreate")
        super.onCreate(savedInstanceState)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.e(TAG,"onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }
    override fun onStart() {
        Log.e(TAG,"onStart")
        super.onStart()
    }
    override fun onResume() {
        Log.e(TAG,"onResume")
        super.onResume()
    }
    override fun onPause() {
        Log.e(TAG,"onPause")
        super.onPause()
    }
    override fun onStop() {
        Log.e(TAG,"onStop")
        super.onStop()
    }
    override fun onDestroyView() {
        Log.e(TAG,"onDestroyView")
        super.onDestroyView()
    }
    override fun onDestroy() {
        Log.e(TAG,"onDestroy")
        super.onDestroy()
    }
    override fun onDetach() {
        Log.e(TAG,"onDetach")
        super.onDetach()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"onViewCreated")
        val btnResetPassword = view?.findViewById<ImageButton>(R.id.image_button_reset)
        val edtEmail = view?.findViewById<TextInputEditText>(R.id.edt_email)

        auth = FirebaseAuth.getInstance()
            btnResetPassword?.setOnClickListener {
            if(TextUtils.isEmpty(edtEmail?.text.toString())){
                edtEmail?.error = "Please enter email"
                return@setOnClickListener
            }
                auth?.sendPasswordResetEmail(edtEmail?.text.toString())
                ?.addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(activity, "Reset password success! ", Toast.LENGTH_LONG).show()
                        parentFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<LogInFragment>(R.id.fragment_container_view)
                            addToBackStack(null)
                        }
                    } else {
                      Toast.makeText(activity, "Reset failed! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}
