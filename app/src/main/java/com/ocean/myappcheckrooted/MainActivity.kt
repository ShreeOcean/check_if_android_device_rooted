package com.ocean.myappcheckrooted

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ocean.myappcheckrooted.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCheckRooted.setOnClickListener {
            if (DeviceRooted.isDeviceRooted){
                AlertDialog.Builder(this)
                    .setTitle("Device Rooted Found")
                    .setMessage("For security reasons, rooted devices are not allowed")
                    .setPositiveButton("OK"){ dialog, _ ->
                        finish()
                    }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }else{
                //login code
                Toast.makeText(this, "This device is not rooted.", Toast.LENGTH_LONG).show()
            }
        }

    }
}