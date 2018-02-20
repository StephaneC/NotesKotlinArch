package com.castrec.stephane.noteskotlinsample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    companion object {

        private val INTENT_USER_ID = "mainactivity"

        fun newIntent(context: Context?): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}