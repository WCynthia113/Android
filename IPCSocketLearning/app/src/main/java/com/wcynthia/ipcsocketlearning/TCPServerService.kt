package com.wcynthia.ipcsocketlearning

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.io.*
import java.lang.Exception
import java.net.ServerSocket
import java.net.Socket
import kotlin.random.Random

class TCPServerService : Service() {
    companion object {
        const val TAG = "TCPServerService"
    }

    lateinit var output: PrintWriter
    lateinit var input: BufferedReader
    private var isServiceDestroyed = false
    private val mMessages = mutableListOf(
        "Hello Body!",
        "用户不在线，请稍后再试",
        "请问你叫什么名字？",
        "厉害了我的哥"
    )

    override fun onCreate() {
        val t = Thread(TCPServer())
        t.start()
        super.onCreate()
    }

    override fun onDestroy() {
        isServiceDestroyed = true
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    inner class TCPServer : Runnable {
        override fun run() {
            var client: Socket? = null
            while (client == null) {
                try {
                    Log.e(TAG, "开始创建服务端Socket")
                    val serverSocket = ServerSocket(8888)
                    client = serverSocket.accept()
                    input = BufferedReader(InputStreamReader(client.getInputStream()))
                    output = PrintWriter(
                        BufferedWriter(OutputStreamWriter(client.getOutputStream())),
                        true
                    )

                    output.println("欢迎来到聊天室")
                } catch (e: Exception) {
                    e.printStackTrace()
                    return
                }
            }

            try {
                responseClient(client)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun responseClient(client: Socket) {
        while (!isServiceDestroyed) {
            val str = input.readLine()
            Log.e(TAG, "message from client:$str")
            if (str == null) {
                return
            }
            val random = java.util.Random()
            val index = random.nextInt(mMessages.size)
            val msg = mMessages[index]
            output.println(msg)
            Log.e(TAG, "send Message:$msg")
        }
        input.close()
        output.close()
        client.close()
    }

}
