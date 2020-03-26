package com.example.servicemessager

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast

class MessengerService : Service() {
    companion object{
        const val MSG_FROM_CLIENT = 0
        const val MSG_FROM_SERVICE = 1
    }
    inner class MessengerHandler: Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                MSG_FROM_CLIENT -> {
                    Log.d("MessengerService","receive msg from client:msg = [${msg.data.getString("key")}]")
                    Toast.makeText(this@MessengerService,"receive msg from client:msg =[${msg.data.getString("key")}]",Toast.LENGTH_SHORT).show()
                    val client = msg.replyTo
                    val replyMsg = Message.obtain(null, MSG_FROM_SERVICE)
                    val bundle = Bundle()
                    bundle.putString("key","i have received message. i will reply later.")
                    replyMsg.data = bundle
                    try {
                        client.send(replyMsg)
                    }catch (e:RemoteException){
                        e.printStackTrace()
                    }
                }
                else ->
                    super.handleMessage(msg)
            }
        }
    }
    private val mMessenger = Messenger(MessengerHandler())
    override fun onBind(intent: Intent): IBinder {
        return mMessenger.binder
    }
}
