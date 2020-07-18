package com.example.drawlayoutdemo

import java.io.Serializable

//组织架构搜索后返回过来的值
data class SearchData(
        /**
         * id : 907
         * deptchain : 914
         * account : 1661255138@qq.com
         * username : A企业A企业
         */
        var id: String? = "",
        var deptchain: String? = "",
        var account: String? = "",
        var username: String? = "",
        var teamName:String?=""

) : Serializable {
    override fun toString(): String {
        return "SearchData(id=$id, deptchain=$deptchain, account=$account, username=$username, teamName=$teamName)"
    }
}