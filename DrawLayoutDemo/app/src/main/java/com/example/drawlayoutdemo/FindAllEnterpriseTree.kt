//package com.example.drawlayoutdemo
//
//import io.reactivex.Observable
//
///**
// * FindAllEnterpriseTree
// *
// * @author  fjx
// * @date    2019/10/15
// * @since   3.0.3
// */
//class FindAllEnterpriseTree {
//
//    /**
//     * 获取3.0.3版本的组织架构前两层数据
//     * @param account 当前登录人的用户账户
//     * @param  isAllOrg  是否查询所有企业:第一层为1，其他层为0，(默认填1)
//     * @param  teamId  企业id
//     * @param   id    当前选中层级id
//     * @param   type   企业/部门/岗位
//     */
//    fun getOrangeDate(account: String, isAllOrg: Int, teamId: Any, id: Any, type: Int, isQueryAll: Int,userId:Long?): Observable<HttpResponse<DateSource>> {
//        return newOrgan.georgette(mapOf("account" to account,
//                "isAllOrg" to isAllOrg,
//                "teamId" to teamId,
//                "id" to id,
//                "type" to type,
//                "userId" to userId,
//                "isQueryAll" to isQueryAll
//        ))
//    }
//
//
//    /**
//     * 获取3.0.3版本的组织架构前两层数据
//     * @param account 用户账户
//     * @param  keywords  关键字
//     * @param  onlyUser  企业id  默认1
//     * 0 只查询id,account,username，
//     * 1 附加查询deptid,deptname,roleid,rolename
//     * 2 只返回id,account,username，但是判断至少有一个岗位
//     * @param   orgIds    企业id的集合
//     * @param   type   企业/部门/岗位
//     */
//    fun getSearchDate(account: String, keywords: String, onlyUser: Int, orgIds: List<Long>): Observable<HttpResponse<List<SearchData>>> {
//        return newOrgan.generate(mapOf("account" to account,
//                "keywords" to keywords,
//                "onlyUser" to onlyUser,
//                "orgIds" to orgIds))
//    }
//
//    /**
//     * 工作台的组织架构
//     * @param username String 类型（用户账户）
//     *
//     * @param type int （默认-1）
//     */
//
//    fun getWorkBenchDate(username: String, type: Int,userId:Long?): Observable<HttpResponse<ArrayList<DataList>>> {
//        return newOrgan.guesswork(mapOf("username" to username, "type" to type,"userId" to userId))
//    }
//
//}