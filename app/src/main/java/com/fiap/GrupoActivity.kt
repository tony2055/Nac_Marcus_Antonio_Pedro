package com.fiap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_grupo.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener




class GrupoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grupo)

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")


        btSalvar.setOnClickListener {
            myRef.setValue(etNomeGrupo.text.toString())

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(String::class.java)
                    tvGrupo.text = value
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value

                }
            })
        }
    }
}
