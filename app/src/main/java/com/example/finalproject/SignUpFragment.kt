package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.finalproject.databinding.ActivitySignupFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment: Fragment() {

    private lateinit var auth: FirebaseAuth
    private var databaseReference: DatabaseReference? = null
    private var database: FirebaseDatabase? = null
    val TAG = "FragmentSignup"
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
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_signup_fragment, container, false)
        Log.e(TAG,"onCreateView")
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG,"onViewCreated")
        val btnLogIn    = view?.findViewById<TextView>(R.id.tv_have_account_signin)
        val btnSignUp   = view?.findViewById<ImageButton>(R.id.image_button_signup)
        val edtEmail    = view?.findViewById<TextInputEditText>(R.id.edt_email)
        val edtPassword = view?.findViewById<TextInputEditText>(R.id.edt_password)
        val edtFullName = view?.findViewById<TextInputEditText>(R.id.edt_fullname)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        btnLogIn?.setOnClickListener{
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<LogInFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
        btnSignUp?.setOnClickListener {
            Log.d("DAN", "click_register")
            Log.d("DAN", "edtFullName?.text.toString()")
            Log.d("DAN", "edtEmail?.text.toString()")
            Log.d("DAN", "edtPassword?.text.toString()")
            if(TextUtils.isEmpty(edtFullName?.text.toString())){
                edtFullName?.error = "Please enter full name "
                return@setOnClickListener
            } else if(TextUtils.isEmpty(edtEmail?.text.toString())) {
                edtEmail?.error="Please enter email "
                return@setOnClickListener
            }else if(TextUtils.isEmpty(edtPassword?.text.toString())) {
                edtPassword?.error="Please enter password "
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(edtEmail?.text.toString(),edtPassword?.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        val currentUser = auth.currentUser
                        val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                        currentUSerDb?.child("fullname")?.setValue(edtFullName?.text.toString())
                        currentUSerDb?.child("email")?.setValue(edtEmail?.text.toString())
                        Toast.makeText(activity, "Registration Success. ", Toast.LENGTH_LONG).show()
                        parentFragmentManager.commit {
                            setReorderingAllowed(true)
                            replace<LogInFragment>(R.id.fragment_container_view)
                            addToBackStack(null)
                        }
                    } else {
                        Toast.makeText(activity, "Registration failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
   }
}


