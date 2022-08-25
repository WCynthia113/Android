package com.sumie.glidelearning.glide.requestManager

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

object Tool {

    private val HASH_MULTIPLIER = 31
    private val HASH_ACCUMULATOR = 17
    private val HEX_CHAR_ARRAY = "0123456789abcdef".toCharArray()
    private val SHA_256_CHARS = CharArray(64)

    private fun sha256BytesToHex(bytes: ByteArray): String {
        synchronized(SHA_256_CHARS) {
            return bytesToHex(bytes, SHA_256_CHARS)
        }
    }

    private fun bytesToHex(bytes: ByteArray, hexChars: CharArray): String {
        var v:Int
        for (j in bytes.indices) {
            v = (bytes[j] and 0xFF.toByte()).toInt()
            hexChars[j * 2] = HEX_CHAR_ARRAY[v ushr 4]
            hexChars[j * 2 + 1] = HEX_CHAR_ARRAY[v and 0x0F]

        }
        return String(hexChars)
    }

    fun getSHA256StrJava(str: String): String {
        val messageDigest: MessageDigest
        var encodeStr: String = ""
        try {
            messageDigest = MessageDigest.getInstance("SHA-256")
            messageDigest.update(str.toByteArray(charset("UTF-8")))
            encodeStr = sha256BytesToHex(messageDigest.digest())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return encodeStr
    }

    private fun byte2Hex(bytes: ByteArray): String? {
        val stringBuffer = StringBuffer()
        var temp: String? = null
        for (i in bytes.indices) {
            temp = Integer.toHexString((bytes[i] and 0xFF.toByte()).toInt())
            if (temp.length == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0")
            }
            stringBuffer.append(temp)
        }
        return stringBuffer.toString()
    }

}