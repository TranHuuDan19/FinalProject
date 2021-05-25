package com.example.finalproject

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LogInFragment: Fragment() {
    val TAG = "FragmentLogin"
     var auth: FirebaseAuth?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_login_fragment, container, false)
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
        val btnSIgnUp = view?.findViewById<TextView>(R.id.tv_no_account_signup)
        val tvForgotPass = view?.findViewById<TextView>(R.id.tv_forgot_password)
        val btnLogIn = view?.findViewById<ImageButton>(R.id.image_button_reset)
        val edtEmail = view?.findViewById<TextInputEditText>(R.id.edt_email)
        val edtPassword = view?.findViewById<TextInputEditText>(R.id.edt_password)
        auth = FirebaseAuth.getInstance()
            btnLogIn?.setOnClickListener {
            Log.d("TAG","Da click login")
            if(TextUtils.isEmpty(edtEmail?.text.toString())){
                edtEmail?.error = "Please enter email"
                return@setOnClickListener
            }
            else if(TextUtils.isEmpty(edtPassword?.text.toString())){
                edtPassword?.error = "Please enter password"
                return@setOnClickListener
            }
            auth?.signInWithEmailAndPassword(edtEmail?.text.toString(),edtPassword?.text.toString())
                ?.addOnCompleteListener {
                    if(it.isSuccessful) {
                        Toast.makeText(activity, "Login successful! ", Toast.LENGTH_LONG).show()
                        parentFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<ProfileFragment>(R.id.fragment_container_view)
                            addToBackStack(null)
                        }
                    } else {
                      Toast.makeText(activity, "Login failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
            btnSIgnUp?.setOnClickListener{
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<SignUpFragment>(R.id.fragment_container_view)
                    addToBackStack(null)
                }
            }
        tvForgotPass?.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ForgotPasswordFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
    }
    private fun reload() {
        Log.e(TAG,"reload")

        parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ProfileFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
    }
}

