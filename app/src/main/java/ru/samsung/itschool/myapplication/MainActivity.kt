package ru.samsung.itschool.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val et_url: EditText = findViewById(R.id.url)
        val firstname: EditText = findViewById(R.id.firstname)
        val lastname: EditText = findViewById(R.id.lastnamet)
        val send: Button = findViewById(R.id.send)
        val result: TextView =findViewById(R.id.result)
        send.setOnClickListener(View.OnClickListener {
            val thread:Thread = object:Thread(){
                override fun run() {
                    val url: URL = URL(et_url.text.toString()+"?firstname="+
                            firstname.text+"&lastname="+lastname.text)
                    val urlConnection:HttpURLConnection = url.openConnection() as HttpURLConnection
                    try {
                        val data  = urlConnection.inputStream.bufferedReader().readLine()
                        result.text = data
                    } finally {
                        urlConnection.disconnect()
                    }
                }
            }
            thread.start()
        })


    }
}