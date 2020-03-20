package com.wcynthia.aidltest
import android.os.Parcel
import android.os.Parcelable
data class Book(val name: String?, val price:Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object{
        @JvmField
        var CREATOR : Parcelable.Creator<Book> = object :Parcelable.Creator<Book>{
            override fun createFromParcel(parcel: Parcel): Book {
                return Book(parcel)
            }

            override fun newArray(size: Int): Array<Book?> {
                return arrayOfNulls(size)
            }
        }
    }
}