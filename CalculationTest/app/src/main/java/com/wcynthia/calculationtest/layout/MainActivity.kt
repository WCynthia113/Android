package com.wcynthia.calculationtest.layout

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.wcynthia.calculationtest.R

class MainActivity : AppCompatActivity() {
    private val controller by lazy {
        Navigation.findNavController(this, R.id.fragment)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (controller.currentDestination?.id == R.id.questionFragment) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.dialog_quit_message))
            builder.setPositiveButton(getString(R.string.dialog_positive_message)) { _, _ ->
                controller.navigateUp()
            }
            builder.setNegativeButton(getString(R.string.dialog_negative_message)) { _, _ ->
            }
            val dialog = builder.create()
            dialog.show()
        }
        else if (controller.currentDestination?.id == R.id.titleFragment){
            finish()
        }
        else {
            controller.navigate(R.id.titleFragment)
        }
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }
}
