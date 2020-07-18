package com.example.drawlayoutdemo.treeview

import com.example.drawlayoutdemo.R
/**
 * @description  节点抽象类（泛型T主要是考虑到ID和parentID有可能是int型也有可能是String型
 * 即这里可以传入Integer或者String，具体什么类型由子类指定
 * ，因为这两种类型比较是否相等的方式不同：一个是用 “==”，一个是用  equals() 函数）
 */
abstract class Node<T> {

    //如果是 -1 的话就递归获取
    //因为是树形结构，所以此处想要得到当前节点的层级
    //，必须递归调用，但是递归效率低下，如果每次都递归获取会严重影响性能，所以我们把第一次
    //得到的结果保存起来避免每次递归获取
    var mLevel = - 1
        get() {
            if (field == - 1) {
                val level = if (mParent == null) 1 else mParent !!.mLevel + 1
                this.mLevel = level
                return field

            }
            return field
        }//当前节点的层级，初始值-1
    //只进行查询的--list
    // var _childrenList: List<Node<T>> = listOf()
    /*   //进行增删操作
     var _childrenList: List<Node<T>> = mutableListOf()*/
    //进行增删操作--并且初始化
    var mChildrenList = mutableListOf<Node<*>>()


    abstract var mCurrentParentId: Long?//当前节点的父ID用于人被点击了，记录他的父ID


    var mParent: Node<*>? = null//父亲节点
    var icon: Int = 0//图标资源ID
    var iconLeft: Int = 0//图标资源ID

    var isExpand = false
        set(isExpand) {
            field = isExpand
            icon = if (isExpand) {
                R.mipmap.expand

            } else {
                R.mipmap.collapse

            }

        }//当前状态是否展开


    private var _isChecked = false //每条item的状态

    abstract val mId: Long?//得到当前节点ID
    abstract val mParentId: Long?//得到当前节点的父ID
    abstract val mLabel: String?//要显示的内容
    abstract val labelPerson: Int? //要显示的人数
    abstract val ordinal: Int?//区分企业为２，部门３ ,0：人-----对应的台返回的type字段: 企业2，部门3，人0
    abstract val enterpriseId: Int//企业ID
    abstract val roleName: String?//岗位
    abstract val hasChild: Int?//判断是否还有子项目
    abstract val hasChildList: Int?//判断子项目的个数
    abstract val mBigId: String?//企业ID:例如 :#244#251#468#  用于后续判断获取当前点击所在的企业ID
    abstract val mLogo:String?//企业头像


    val isRoot: Boolean
        get() = mParent == null

    val isLeaf: Boolean
        get() = mChildrenList.isEmpty()


    abstract fun parent(dest: Node<*>): Boolean //判断当前节点是否是dest的父亲节点
    abstract fun child(dest: Node<*>): Boolean //判断当前节点是否是dest的孩子节点


    fun checked(): Boolean {
        return _isChecked
    }

    fun setIsChecked(_isChecked: Boolean) {
        this._isChecked = _isChecked
    }

    override fun toString(): String {
        return "Node(mChildrenList=$mChildrenList, mCurrentParentId=$mCurrentParentId, mParent=$mParent, icon=$icon, iconLeft=$iconLeft, isExpand=$isExpand, _isChecked=$_isChecked, mId=$mId, mParentId=$mParentId, mLabel=$mLabel, labelPerson=$labelPerson, ordinal=$ordinal, enterpriseId=$enterpriseId, roleName=$roleName, hasChild=$hasChild, hasChildList=$hasChildList, mBigId=$mBigId, mProfile=$mLogo)"
    }


}
