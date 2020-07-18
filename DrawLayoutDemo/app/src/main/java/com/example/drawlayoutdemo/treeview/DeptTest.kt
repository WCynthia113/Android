package com.hp.goalgo.ui.main.treeview

/**
 * Created by HQOCSHheqing on 2019/10/10.
 *
 * @description 部门类（继承Node），此处的泛型Integer是因为ID和parentID都为int
 * ，如果为String传入泛型String即可
 */
/*class Dept : Node<Int> {

    var id: Int? = 0//部门ID
    var parentId: Int? = 0//父亲节点ID

    var name: String? = null

    constructor(id: Int, parentId: Int, name: String) {
        this.id = id
        this.parentId = parentId
        this.name = name
    }


    override var _label:String=""
    override var _id:Int=0

    override var _parentId:Int=0





    //开始实现继承的Node的抽象方法

//结束实现继承的Node的抽象方法

    override fun parent(dest: Node<*>): Boolean {
        return if (id == (dest._parentId as Int).toInt()) {
            true
        } else false
    }

    override fun child(dest: Node<*>): Boolean {
        return if (parentId == (dest._id as Int).toInt()) {
            true
        } else false
    }


}*/
