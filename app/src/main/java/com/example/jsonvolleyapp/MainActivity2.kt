package com.example.jsonvolleyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setup();
    }

    fun setup(){
        // Con Volley
        val textView = findViewById<TextView>(R.id.textView)
        val queue = Volley.newRequestQueue(this)
        val url = "https://gorest.co.in/public/v1/users"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonObj = JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1))
                val datajson = jsonObj.getJSONArray("data")
                var cadenadedatos: String = ""
                for (i in 0..datajson!!.length() - 1) {
                    var name = datajson.getJSONObject(i).getString("name").toString()
                    var id = datajson.getJSONObject(i).getString("id").toString()
                    var email = datajson.getJSONObject(i).getString("email").toString()
                    var gender = datajson.getJSONObject(i).getString("gender").toString()
                    var status = datajson.getJSONObject(i).getString("status").toString()
                    cadenadedatos += " ID:$id " +
                            "\n Nombre:$name " +
                            "\n Email:$email " +
                            "\n Género: $gender " +
                            "\n Status:$status \n\n"
                }
                textView.text=cadenadedatos.toString();
            },
            { textView.text = "That didn't work!" })
        queue.add(stringRequest)
    }
}