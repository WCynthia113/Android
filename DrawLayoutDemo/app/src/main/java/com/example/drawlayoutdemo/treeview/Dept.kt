package com.hp.goalgo.ui.main.treeview

import com.example.drawlayoutdemo.treeview.Node

/**
 * @description 部门类（继承Node），此处的泛型Integer是因为ID和parentID都为int
 * ，如果为String传入泛型String即可
 */
class Dept(var id: Long//部门ID
           , private val parentId: Long?//父亲节点ID
           , name: String?
           , override val labelPerson: Int = 0//部门名称
           , override val ordinal: Int?
           , private val rename: String? = ""//岗位名称
           , override val hasChild: Int?//是否还有子项目
           , override val hasChildList: Int?//有多少个子项目的条数
           , override val mBigId: String? //存储父ID类似如:#244#251#467# 用于后续判断获取当前点击所在的企业ID
           , override val mLogo: String?//返回的头像
           , override var mCurrentParentId: Long? = - 1
) : Node<Int>() {
    override var mLabel: String? = null
        private set//部门名称

    override var enterpriseId: Int = 0
        private set//企业ID


    override val roleName: String?
        get() = rename



    /**
     * 此处返回节点ID
     */
    override val mId: Long
        get() = id

    /**
     * 此处返回父亲节点ID
     */

    override val mParentId: Long?
        get() = parentId


    init {
        this.mLabel = name
    }


    override fun parent(dest: Node<*>): Boolean {

        return id.toString() == (parentId.toString())
    }

    override fun child(dest: Node<*>): Boolean {

        return parentId.toString() == (dest.mId.toString())
    }

}
