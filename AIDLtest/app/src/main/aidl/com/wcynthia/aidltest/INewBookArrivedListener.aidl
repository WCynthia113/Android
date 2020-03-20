// INewBookArrivedListener.aidl
package com.wcynthia.aidltest;
import com.wcynthia.aidltest.Book;
// Declare any non-default types here with import statements

interface INewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}
