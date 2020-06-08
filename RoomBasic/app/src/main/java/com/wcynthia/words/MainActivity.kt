package com.wcynthia.words

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.wcynthia.words.entity.Word
import com.wcynthia.words.viewModel.WordViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = Navigation.findNavController(findViewById(R.id.fragment))
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(findViewById(R.id.fragment))
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}

