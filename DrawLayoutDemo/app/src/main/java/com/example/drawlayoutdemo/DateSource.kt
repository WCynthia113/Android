package com.example.drawlayoutdemo

import android.annotation.SuppressLint

import java.io.Serializable

@SuppressLint("ParcelCreator")// 用于处理 Lint 的错误提示
/**
 * returnCode : 0
 * returnMessage : succeed
 * data : []
 * dataList : [{"id":907,"rid":914,"account":"1661255138@qq.com","name":"A企业A企业","type":2,"hasChild":1,"person":4,"childs":[{"id":909,"rid":916,"account":null,"name":"B部门","type":3,"hasChild":1,"person":1,"childs":[],"departParent":"#907#909#","roleId":null,"roleName":null,"ordinal":2}],"departParent":"#907#","roleId":null,"roleName":null,"ordinal":0},{"id":786,"rid":792,"account":"13438157489","name":"111","type":2,"hasChild":1,"person":4,"childs":[{"id":787,"rid":793,"account":null,"name":"部门","type":3,"hasChild":1,"person":2,"childs":[],"departParent":"#786#787#","roleId":null,"roleName":null,"ordinal":1},{"id":788,"rid":794,"account":null,"name":"bumen2","type":3,"hasChild":1,"person":2,"childs":[],"departParent":"#786#788#","roleId":null,"roleName":null,"ordinal":2}],"departParent":"#786#","roleId":null,"roleName":null,"ordinal":0},{"id":244,"rid":250,"account":"18382244540","name":"成都海谱科技有限公司","type":2,"hasChild":1,"person":42,"childs":[{"id":251,"rid":257,"account":null,"name":"goalgo项目组","type":3,"hasChild":1,"person":33,"childs":[],"departParent":"#244#251#","roleId":null,"roleName":null,"ordinal":3}],"departParent":"#244#","roleId":null,"roleName":null,"ordinal":0},{"id":23,"rid":28,"account":"1661255138@qq.com","name":"目标GO BUG管理","type":2,"hasChild":1,"person":19,"childs":[{"id":218,"rid":224,"account":null,"name":"无部门人员","type":3,"hasChild":1,"person":16,"childs":[],"departParent":"#23#218#","roleId":null,"roleName":null,"ordinal":1}],"departParent":"#23#","roleId":null,"roleName":null,"ordinal":0},{"id":652,"rid":658,"account":"13438157489","name":"新企业","type":2,"hasChild":1,"person":4,"childs":[{"id":653,"rid":659,"account":null,"name":"部门","type":3,"hasChild":1,"person":3,"childs":[],"departParent":"#652#653#","roleId":null,"roleName":null,"ordinal":1},{"id":791,"rid":797,"account":null,"name":"bumen2","type":3,"hasChild":1,"person":1,"childs":[],"departParent":"#652#791#","roleId":null,"roleName":null,"ordinal":2}],"departParent":"#652#","roleId":null,"roleName":null,"ordinal":0},{"id":834,"rid":840,"account":"1661255138@qq.com","name":"test  系统通知","type":2,"hasChild":1,"person":2,"childs":[{"id":835,"rid":841,"account":null,"name":"1","type":3,"hasChild":1,"person":1,"childs":[],"departParent":"#834#835#","roleId":null,"roleName":null,"ordinal":1}],"departParent":"#834#","roleId":null,"roleName":null,"ordinal":0},{"id":842,"rid":848,"account":"18030431970","name":"测试审批","type":2,"hasChild":1,"person":3,"childs":[{"id":843,"rid":849,"account":null,"name":"部门","type":3,"hasChild":1,"person":2,"childs":[],"departParent":"#842#843#","roleId":null,"roleName":null,"ordinal":1}],"departParent":"#842#","roleId":null,"roleName":null,"ordinal":0}]
 */


/**
 * 声明实体类的时候前面要加上data
 * 实体类里有多个数组，可以直接在这个类里直接data class DataListBean
 * 序列化的时候可以直接在类后面添加": Serializable"即可
 * #kotlin的构造函数是直接在类名的DataListBean(
 * 　　　　var id: Int = 0,
 *       　var name: String? = null,
 *        　var roleId: Any? = null,
 *      　 var childs: List<ChildsBean>? = null)
 *
 * */

data class DateSource(
        var orgIds: List<Long>? = null,
        var childs: MutableList<ChildsBean>? = null,
        var user: User) : Serializable


data class ChildsBean(
        /**
         * id : 907
         * rid : 914
         * account : 1661255138@qq.com
         * name : A企业A企业
         * type : 2
         * hasChild : 1
         * person : 4
         * childs : [{"id":909,"rid":916,"account":null,"name":"B部门","type":3,"hasChild":1,"person":1,"childs":[],"departParent":"#907#909#","roleId":null,"roleName":null,"ordinal":2}]
         * departParent : #907#
         * roleId : null
         * roleName : null
         * ordinal : 0
         * profile:返回的企业头像
         */
        var id: Long = 0,
        var rid: Int? = 0,
        var account: String? = null,
        var name: String? = null,
        var type: Int? = 0,
        var hasChild: Int? = 0,
        var person: Int = 0,
        /* var departParent: String? =null,*/
        var departParent: String? = "",
        // var departParent: Int?= 0,
        var roleId: Any? = null,
        var roleName: String? = null,
        var ordinal: Int = 0,
        var profile: String = "",
        var logo: String? = "",
        var childs: List<ChildsBean>? = null

) : Serializable {
    override fun toString(): String {
        return "ChildsBean(id=$id, rid=$rid, account=$account, name=$name, type=$type, hasChild=$hasChild, person=$person, departParent=$departParent, roleId=$roleId, roleName=$roleName, ordinal=$ordinal, childs=$childs)"
    }
}


data class User(
        /**
         * id : 909
         * rid : 916
         * account : null
         * name : B部门
         * type : 3
         * hasChild : 1
         * person : 1
         * childs : []
         * departParent : #907#909#
         * roleId : null
         * roleName : null
         * ordinal : 2
         */

        var id: Int = 0,
        var rid: Int = 0,
        var account: Any? = null,
        var name: String? = null,
        var type: Int = 0,
        var hasChild: Int = 0,
        var person: Int = 0,
        var departParent: String? = "",
        var roleId: Any? = null,
        var roleName: Any? = null,
        var ordinal: Int = 0) : Serializable

//工作台组织架构的数据格式
data class DataList(
        var id: Long = 0,
        var teamId: Long? = 0,
        var name: String? = null,
        var shortName: String? = null,
        var type: Int? = 0,
        var responsibleUserId: Long? = 0,
        var attachInfoId: Long? = 0,
        var responsibleUserAccount: String? = null,
        var hasAuth: Long? = 0,
        var unreadNotice: Long? = 0,
        var logo: String? = null,
        var isLast: Long? = 0,
        var followId: Long? = 0,
        val workbenchCount: Int? = 0,//待处理的总数量
        val communicationCount: Int? = 0,//沟通的数量
        var isSelect: Boolean = false,//默认false:未点击, true:点击
        var profile: String = "",//当前账号用户登录的头像
        var businessDataAddress: String? = ""//中台地址
) : Serializable


