package com.memo.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.base.base.dialog.LoadingDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoadingDialog().show(supportFragmentManager)
    }
}