package com.wcynthia.aidltest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.net.Socket

class MainActivity : AppCompatActivity() {

    class MessageHandler:Handler(){
        override fun handleMessage(msg: Message) {
            when(msg.what){
                1->{Log.e("MainActivity","handleMessage:Arrived New Book:${msg.obj}")}
                else-> super.handleMessage(msg)
            }
        }
    }
    private val mHandler = MessageHandler()
    private lateinit var bookManager:IBookManager
    private val listener = object :INewBookArrivedListener.Stub(){
        override fun onNewBookArrived(newBook: Book?) {
            mHandler.obtainMessage(1,newBook).sendToTarget()
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,BookManagerService::class.java)
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE)
        button.setOnClickListener {
            try {

                val list = bookManager.bookList
                Log.e("MainActivity","$list")
            }catch (e:RemoteException){
                e.printStackTrace()
            }
        }
        button2.setOnClickListener {
            try {
                bookManager.addBook(Book("天龙八部",90))
            }catch (e:RemoteException){
                e.printStackTrace()
            }
        }
    }
    private val mServiceConnection = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            bookManager = IBookManager.Stub.asInterface(service)
            try {
                bookManager.registerListener(listener)
            }catch (e:RemoteException){
                e.printStackTrace()
            }
        }

    }

    override fun onDestroy() {
        if (bookManager!=null&&bookManager.asBinder().isBinderAlive){
            try {
                bookManager.unregisterListener(listener)
            }catch (e:RemoteException){
                e.printStackTrace()
            }
        }
        unbindService(mServiceConnection)
        super.onDestroy()
    }
}
