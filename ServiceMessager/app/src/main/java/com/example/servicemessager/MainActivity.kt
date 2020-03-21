package com.example.servicemessager

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.servicemessager.MessengerService.Companion.MSG_FROM_CLIENT
import com.example.servicemessager.MessengerService.Companion.MSG_FROM_SERVICE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mGetReplyMessenger = Messenger(MessengerHandler())//目标向Activity的Messenger
    lateinit var mService:Messenger//目标向Service的Messenger
    inner class MessengerHandler: Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                MSG_FROM_SERVICE -> {
                    Log.d("MainActivity","receive msg from service:msg = [${msg.data.getString("key")}]")
                    Toast.makeText(this@MainActivity,"receive msg from service:msg =[${msg.data.getString("key")}]",Toast.LENGTH_SHORT).show()
                }
                else ->
                    super.handleMessage(msg)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            bindService()
        }
    }
    private fun bindService(){
        val mIntent = Intent(this,MessengerService::class.java)
        bindService(mIntent,mServiceConnection,Context.BIND_AUTO_CREATE)
    }
    fun sendMessage(){
        val msg = Message.obtain(null,MSG_FROM_CLIENT)
        val data = Bundle()
        data.putString("key","Hello!This is client.")
        msg.data = data
        msg.replyTo = mGetReplyMessenger
        try {
            mService.send(msg)
        }catch (e:RemoteException){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        unbindService(mServiceConnection)
        super.onDestroy()
    }
    private val mServiceConnection = object :ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            sendMessage()
        }


    }
}
