package com.example.basketballscoring

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.basketballscoring.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateViewModelFactory
//import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val KEY_NUMBER = "my_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val sp = getPreferences(Context.MODE_PRIVATE)
//        val editor = sp.edit()
//        editor.putInt("NUMBER",100).apply()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        viewModel = ViewModelProvider.AndroidViewModelFactory(application).create(MyViewModel::class.java)
//        viewModel = ViewModelProvider(this,SavedStateViewModelFactory(application,this)).get(MyViewModel::class.java)
        viewModel = ViewModelProvider(this,SavedStateViewModelFactory(application,this)).get(MyViewModel::class.java)
//        if (savedInstanceState != null) {
//            viewModel.getATeamScore().value = savedInstanceState.getInt(KEY_NUMBER)
//        }
        binding.data = viewModel
        binding.lifecycleOwner = this
        val callableThreadTest = CallableThreadTest()
        val futureTask = FutureTask<Int>(callableThreadTest)
        for (i in 1..100){
            Log.e("printOut","${Thread.currentThread().name}的循环变量i的值$i")
            if(i == 20){
                val thread = Thread(futureTask,"有返回值的线程")
                thread.start()
            }
        }


        try {
            Log.e("printOut","子线程返回值${futureTask.get()}")
        }catch (e:Exception){
            Log.e("printOut","${e.message}")
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.save()
    }

    public class CallableThreadTest:Callable<Int>{

        override fun call(): Int {
            var index = 0
            for (i in 0..100){
                index=i
                Log.e("printOut","${Thread.currentThread().name} $index")
            }
            return index
        }

    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt(KEY_NUMBER, viewModel.getATeamScore().value!!)
//    }
}
