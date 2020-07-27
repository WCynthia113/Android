package com.example.intnumtochinesenum

import java.lang.StringBuilder

/**
 * Created by Wenxi on 2020/7/27 11:14
 * 将数字转换为中文数字，例如1转为一
 * 范围为 1 to [Int.MAX_VALUE]
 */
object TextNumUtil {
    private val ChinaDigit = listOf("零", "一", "二", "三", "四", "五", "六", "七", "八", "九")
    private val UNIT = listOf("", "十", "百", "千")
    private val BIG_UNIT = listOf("", "万", "亿")
    private lateinit var digitString: String
    private var isPreSegmentZero = true

    fun Int.translateToChinese(): String {
        val chineseBuff = StringBuilder()
        digitString = this.toString()
        //将数每四位进行划分segmentNum段
        val segmentNum = (digitString.length - 1) / 4
        //获取头部的位数
        val headLength = (digitString.length - 1) % 4
        chineseBuff.append(partTranslate(0, headLength) + BIG_UNIT[segmentNum])
        if (segmentNum == 0) {
            return chineseBuff.toString()
        }
        for (i in 1..segmentNum) {
            chineseBuff.append(
                partTranslate(
                    (i - 1) * 4 + headLength + 1,
                    i * 4 + headLength
                )
            )
            if (!isPreSegmentZero) {
                chineseBuff.append(BIG_UNIT[segmentNum - i])
            }
        }
        return chineseBuff.toString()
    }

    private fun partTranslate(startIndex: Int, endIndex: Int): String {
        isPreSegmentZero = true
        var isPreDigitZero = false
        val chineseBuff = StringBuilder()
        for (i in startIndex..endIndex) {
            val currentNum: Int = digitString[i] - '0'
            isPreDigitZero = if (currentNum != 0) {
                if (isPreDigitZero) {
                    chineseBuff.append(ChinaDigit[0])
                }
                chineseBuff.append(ChinaDigit[currentNum] + UNIT[endIndex - i])
                isPreSegmentZero = false
                false
            } else {
                true
            }
        }
        return chineseBuff.toString()
    }
}