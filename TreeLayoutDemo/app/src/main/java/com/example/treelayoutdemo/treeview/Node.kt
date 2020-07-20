package com.example.treelayoutdemo.treeview

import android.content.Context
import android.content.res.Resources
import android.text.SpannableString
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import com.example.treelayoutdemo.R

/**
 * @description  节点抽象类（泛型T主要是考虑到ID和parentID有可能是int型也有可能是String型
 * 即这里可以传入Integer或者String，具体什么类型由子类指定
 * ，因为这两种类型比较是否相等的方式不同：一个是用 “==”，一个是用  equals() 函数）
 */
abstract class Node(private val context: Context) {

    //如果是 -1 的话就递归获取
    //因为是树形结构，所以此处想要得到当前节点的层级
    //，必须递归调用，但是递归效率低下，如果每次都递归获取会严重影响性能，所以我们把第一次
    //得到的结果保存起来避免每次递归获取
    var mLevel = -1
        get() {
            if (field == -1) {
                val level = if (mParent == null) 1 else mParent!!.mLevel + 1
                field = level
            }
            return field
        }//当前节点的层级，初始值-1

    //进行增删操作--并且初始化
    var mChildrenList = arrayListOf<Node>()//孩子节点
    var mParent: Node? = null//父亲节点
    var isExpand = false//当前状态是否展开
    var isSelected = false //当前是否是被选中状态
    var hasNext = false//此层是否还有下一个
    var isFirst = false//是否是此层第一个Node
    private var edgeView = ImageView(context)
    private var treeEdge: MutableList<Int> = mutableListOf()

    val isRoot: Boolean
        get() = mParent == null

    val isLeaf: Boolean
        get() = hasChild()

    abstract fun getName(): SpannableString//要显示的内容
    abstract fun hasChild(): Boolean
    abstract fun isParent(dest: Node): Boolean //判断当前节点是否是dest的父亲节点
    abstract fun isChild(dest: Node): Boolean //判断当前节点是否是dest的孩子节点
    abstract fun getStartIcon(): Int//要显示的左图标资源
    abstract fun getEndIcon(): Int//要显示的右图标资源
    fun getEdge(): List<Int> {
        if (treeEdge.isEmpty()) {
            if (mParent != null) {
                treeEdge.addAll(getParentTreeEdge(mParent!!))
            }
            when {
                isFirst && hasNext ->
                    treeEdge.add(R.mipmap.ic_launcher)
                isFirst && !hasNext ->
                    treeEdge.add(R.mipmap.ic_launcher)
                !isFirst && hasNext ->
                    treeEdge.add(R.mipmap.ic_launcher)
                !isFirst && !hasNext ->
                    treeEdge.add(R.mipmap.ic_launcher)
            }
        }
        return treeEdge
    }

    private fun getParentTreeEdge(node: Node): List<Int> {
        val viewList = mutableListOf<Int>()
        if (node.mParent != null) {
            viewList.addAll(getParentTreeEdge(node.mParent!!))
        }
        if (hasNext) {
            viewList.add(R.mipmap.ic_launcher)
        } else {
            viewList.add(R.mipmap.ic_launcher)
        }
        return viewList
    }

//    override fun toString(): String {
//        return "Node(mChildrenList=$mChildrenList, mParent=$mParent,isExpand=$isExpand, isSelected=$isSelected)"
//    }

    private fun dpToPixel(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            Resources.getSystem().displayMetrics
        )
    }
}
