package com.example.treelayoutdemo.treeview


/**
 * @description 部门类（继承Node），此处的泛型Integer是因为ID和parentID都为int
 * ，如果为String传入泛型String即可
 */
class Dept(
    var id: Long//部门ID
    , private val parentId: Long?//父亲节点ID
    , name: String?
    , val labelPerson: Int = 0//部门名称
    , val ordinal: Int?
    , val rename: String? = ""//岗位名称
    , private val hasChild: Int?//是否还有子项目
    , val peopleNumber: Int?//有多少个子项目的条数
    , val mBigId: String? //存储父ID类似如:#244#251#467# 用于后续判断获取当前点击所在的企业ID
    , val mLogo: String?//返回的头像
    , var mCurrentParentId: Long? = -1

) : Node(
) {
    override fun hasChild(): Boolean {
        return hasChild != 0
    }

    override fun isParent(dest: Node): Boolean {

    }

    override fun isChild(dest: Node): Boolean {
        TODO("Not yet implemented")
    }


}
