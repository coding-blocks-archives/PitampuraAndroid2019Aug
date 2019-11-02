package com.android.firebasedatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val db by lazy {
        FirebaseDatabase.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val ref = db.reference.child("username")
            val key = ref.push().key
            key?.let { it1 -> ref.child(it1).setValue(editText.text.toString()) }
        }


        val list = arrayListOf<String>()
        db.reference.child("username").child("1").addValueEventListener(
            object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    textView.setText(textView.text.toString() + p0.value.toString())
                }

                override fun onCancelled(p0: DatabaseError) {
                }

            }
        )

        db.reference.child("username")
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                }

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                    list.add(p0.value.toString())
                    textView.text = ""
                    list.forEach {
                        textView.setText(textView.text.toString() + it)
                    }
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    textView.text = ""

                }

            })
    }
}
