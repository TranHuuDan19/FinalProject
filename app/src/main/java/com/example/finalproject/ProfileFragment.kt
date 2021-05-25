package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.finalproject.databinding.ActivityProfileFragmentBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment: Fragment(){

        lateinit var auth: FirebaseAuth
        var databaseReference: DatabaseReference? = null
        var database: FirebaseDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_profile_fragment, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fullNameText = view?.findViewById<TextView>(R.id.fullNameText)
        val emailText = view?.findViewById<TextView>(R.id.emailText)
        val btnlogout = view?.findViewById<ImageButton>(R.id.btnlogout)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                fullNameText?.text =
                    "" + snapshot.child("fullname")?.value.toString()
                emailText?.text =
                    "" + snapshot.child("email")?.value.toString()
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
        btnlogout?.setOnClickListener {
            Log.d("TAG"," "+fullNameText?.text.toString())
            Log.d("TAG"," "+emailText?.text.toString())
            auth.signOut()
                parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<LogInFragment>(R.id.fragment_container_view)
                addToBackStack(null)
            }
        }
    }
}

