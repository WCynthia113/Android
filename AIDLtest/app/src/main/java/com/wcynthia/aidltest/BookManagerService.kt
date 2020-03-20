package com.wcynthia.aidltest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteCallbackList
import android.os.RemoteException
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

class BookManagerService : Service() {
    companion object {
        const val TAG = "BookManagerService"
    }

    private val mBookList = CopyOnWriteArrayList<Book>()
    private val mListeners = RemoteCallbackList<INewBookArrivedListener>()
    private val isServiceDestroyed = AtomicBoolean(false)
    private val mBinder = object : IBookManager.Stub() {
        override fun getBookList(): MutableList<Book> {
            return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun registerListener(listener: INewBookArrivedListener?) {
            mListeners.register(listener)
        }

        override fun unregisterListener(listener: INewBookArrivedListener?) {
            mListeners.unregister(listener)
        }

    }

    inner class ServiceWorker:Runnable {
        override fun run() {
            while (!isServiceDestroyed.get()){
                try {
                    Thread.sleep(5000)
                }catch (e:InterruptedException){
                    e.printStackTrace()
                }
                val bookPrice = mBookList.size+1
                val newBook = Book("new book",bookPrice)
                try {
                    onNewBookArrived(newBook)
                }catch (e:RemoteException){
                    e.printStackTrace()
                }
            }
        }
    }

    private fun onNewBookArrived(newBook: Book){
        mBookList.add(newBook)
        val count = mListeners.beginBroadcast()
        for (i  in 0 until count){
            mListeners.getBroadcastItem(i)?.onNewBookArrived(newBook)
        }
        mListeners.finishBroadcast()
    }
    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book("老人与海", 50))
        mBookList.add(Book("失落城堡", 10))
        val t = Thread(ServiceWorker())
        t.start()
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onDestroy() {
        isServiceDestroyed.set(true)
        super.onDestroy()
    }
}
