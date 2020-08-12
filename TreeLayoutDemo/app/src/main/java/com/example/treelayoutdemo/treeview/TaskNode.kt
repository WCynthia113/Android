package com.example.treelayoutdemo.treeview

import android.content.Context
import android.os.SystemClock
import android.text.SpannableString
import com.example.treelayoutdemo.R
import java.util.concurrent.TimeUnit

/**
 * Created by Wenxi on 2020/7/20 15:28
 */
class TaskNode(
    context: Context,
    val id: Int,
    val name: String,
    val startTime: String,
    val endTime: String,
    val subTaskNum: Int,
    val parentId: Int?
) : Node(context) {
    override fun getName(): SpannableString {
        return SpannableString(name)
    }

    override fun hasChild(): Boolean {
        return subTaskNum != 0
    }

    override fun isParent(dest: Node): Boolean {
        return (dest as TaskNode).parentId == this.id
    }

    override fun isChild(dest: Node): Boolean {
        return (dest as TaskNode).id == this.parentId
    }
}