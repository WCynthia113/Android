// IBookManager.aidl
package com.wcynthia.aidltest;
import com.wcynthia.aidltest.Book;
import com.wcynthia.aidltest.INewBookArrivedListener;
// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
  List<Book> getBookList();
  void addBook(in Book book);

  void registerListener(INewBookArrivedListener listener);
  void unregisterListener(INewBookArrivedListener listener);

}
