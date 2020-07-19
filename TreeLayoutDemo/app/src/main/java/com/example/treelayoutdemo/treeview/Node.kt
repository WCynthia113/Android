package com.example.treelayoutdemo.treeview

/**
 * @description  节点抽象类（泛型T主要是考虑到ID和parentID有可能是int型也有可能是String型
 * 即这里可以传入Integer或者String，具体什么类型由子类指定
 * ，因为这两种类型比较是否相等的方式不同：一个是用 “==”，一个是用  equals() 函数）
 */
abstract class Node {

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
    var mChildrenList = mutableListOf<Node>()//孩子节点
    var mParent: Node? = null//父亲节点
    var expandIcon = 0//展开图标资源ID
    var collapseIcon = 0//收起图标资源ID
    var itemIcon: Int = 0//图标资源ID 设置为-1则不需要加载
    var treeEdgeDraw = 0//树边图
    var isExpand = false//当前状态是否展开
    private var _isChecked = false //每条item的状态
    private var nodeType = ""


    val isRoot: Boolean
        get() = mParent == null

    val isLeaf: Boolean
        get() = hasChild()

    fun checked(): Boolean {
        return _isChecked
    }

    fun setIsChecked(_isChecked: Boolean) {
        this._isChecked = _isChecked
    }

    abstract fun getName():String//要显示的内容
    abstract fun hasChild(): Boolean
    abstract fun isParent(dest: Node): Boolean //判断当前节点是否是dest的父亲节点
    abstract fun isChild(dest: Node): Boolean //判断当前节点是否是dest的孩子节点
    override fun toString(): String {
        return "Node(mChildrenList=$mChildrenList, mParent=$mParent, expandIcon=$expandIcon, itemIcon=$itemIcon, isExpand=$isExpand, _isChecked=$_isChecked)"
    }
}
