package com.example.treelayoutdemo.treeview

object NodeHelper {
    fun ArrayList<Node>.sortNode() {
        val rootNodes = mutableListOf<Node>()
        var m: Node
        var n: Node
        //两个for循环整理出所有数据之间的父子关系，最后会构造出一个森林（就是可能有多棵树）
        for (i in 0 until this.size) {
            m = this[i]
            for (j in i + 1 until this.size) {
                n = this[j]
                if (m.isParent(n)) {
                    m.mChildrenList.add(n)
                    n.mParent = m
                } else if (m.isChild(n)) {
                    n.mChildrenList.add(m)
                    m.mParent = n
                }
            }
        }
        //找出所有的树根
        for (i in 0 until size) {
            m = this[i]
            if (m.isRoot) {
                rootNodes.add(m)
            }
        }
        this.clear()
        this.addAll(rootNodes)//返回所有的根节点
        rootNodes.clear()
    }

    fun Node.isParentOf(node: Node): Boolean {

        node.javaClass.name
        return node.mParent == this
    }

    fun Node.isChildOf(node: Node): Boolean {
        return this.mParent == node
    }

    fun List<Node>.setNodeParam() {
        forEachIndexed { index, node ->
            if (index == 0) {
                node.isFirst = true
            }
            if (index != this.size - 1) {
                node.hasNext = true
            }
            if (node.hasChild() && node.mChildrenList.isNotEmpty()) {
                node.mChildrenList.setNodeParam()
            }
        }
    }
}

