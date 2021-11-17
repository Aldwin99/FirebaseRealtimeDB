package com.example.firebaserealtimedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.json.JSONObject

private lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDB = FirebaseDatabase.getInstance()
        database = myDB.reference

        //writeNewUser("001", "nombre1", "aldairsito@mail")

val etId =findViewById<EditText>(R.id.etUserId)
val etNombre = findViewById<EditText>(R.id.etUserName)
val etCorreo = findViewById<EditText>(R.id.etUserEmail)
val btnSend = findViewById<Button>(R.id.btnSet)
val btnRecibe = findViewById<Button>(R.id.btnGet)
        val  IdToGet= findViewById<EditText>(R.id.etUserIdToGet)

        btnSend.setOnClickListener {
            writeNewUser(etId.text.toString(), etNombre.text.toString(), etCorreo.text.toString())
                etId.text.clear()
                etNombre.text.clear()
                etCorreo.text.clear()


}

        btnRecibe.setOnClickListener {
            getUser(IdToGet.text.toString())
            etId.text.clear()
            etNombre.text.clear()
            etCorreo.text.clear()


        }


    }



    fun getUser(userId: String) {
        database.child("users").child(userId).get().addOnSuccessListener { record ->
             val json = JSONObject(record.value.toString())
           Log.d("","nombre${json.getString("nombre")} correo:${json.getString("correo")}")
        }


    }


    fun writeNewUser(userId: String, name: String, email: String) {
        val user = User(name, email)
        database.child("users").child(userId).setValue(user)


    }


    class User(name: String, email: String) {
        val nombre = name
        val correo = email
    }
}