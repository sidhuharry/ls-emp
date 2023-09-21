package com.block.demo.lsemp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.block.demo.lsemp.R

/**
 * Base activity facilitates common setup throughout the app.
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setup action bar common in the app here
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
