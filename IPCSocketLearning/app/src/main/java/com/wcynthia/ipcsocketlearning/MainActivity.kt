package com.wcynthia.ipcsocketlearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.RemoteException
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "TCPClientActivity"
        const val MSG_RECEIVED = 0X10
        const val MSG_READY = 0X11
    }

    private var output: PrintWriter? = null
    private lateinit var input: BufferedReader
    private lateinit var mClientSocket: Socket
    lateinit var stringBuilder: StringBuilder
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_READY -> {
                    Log.e(TAG, "创建Socket完成")
                    button2.isEnabled = true
                }
                MSG_RECEIVED -> {
                    Log.e(TAG, "接收到消息")
                    stringBuilder.append(msg.obj).append("\n")
                    textView.text = stringBuilder.toString()
                }
                else -> {
                    super.handleMessage(msg)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button2.isEnabled = false
        stringBuilder = StringBuilder()
        button2.setOnClickListener {
            if (output != null) {
                val msg = editText.text.toString()

                //网络操作不能在主线程执行
                val t = object : Thread() {
                    override fun run() {
                        output?.println(msg)
                    }
                }
                t.start()
                editText.setText("")
                val time = formatDateTime(System.currentTimeMillis())
                val showMsg = "self $time:$msg\n"
                stringBuilder.append(showMsg)
            }
        }
        val intent = Intent(this, TCPServerService::class.java)
        startService(intent)
        val t = object : Thread() {
            override fun run() {
                connectTcpServer()
            }
        }
        t.start()
    }

    private fun formatDateTime(time: Long): String {
        return SimpleDateFormat("(HH:mm:ss)", Locale.getDefault()).format(Date(time))
    }

    private fun connectTcpServer() {
        var socket: Socket? = null
        while (socket == null) {
            try {
                Log.e(TAG, "开始创建Socket")
                socket = Socket("localhost", 8888)
                Log.e(TAG, socket.toString())
                mClientSocket = socket
                output =
                    PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
                input = BufferedReader(InputStreamReader(socket.getInputStream()))
                mHandler.sendEmptyMessage(MSG_READY)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        while (!isFinishing) {
            responseService()
        }
    }

    private fun responseService() {
        try {
            val msg = input.readLine()
            val time = formatDateTime(System.currentTimeMillis())
            val showedMsg = "server $time:$msg\n"
            mHandler.obtainMessage(MSG_RECEIVED, showedMsg).sendToTarget()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput()
                mClientSocket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        super.onDestroy()
    }
}
