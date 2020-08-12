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


    fun List<Node>.setNodeParam() {
        forEachIndexed { index, node ->
            if (index == 0) {
                node.isFirst = true
            }
            if (index != this.lastIndex) {
                node.hasNext = true
            }
            if (node.hasChild() && !node.mChildrenList.isNullOrEmpty()) {
                node.mChildrenList.setNodeParam()
            }
        }
    }


    fun ArrayList<Node>.setDefaultExpandLevel(level: Int) {
        var i = 0
        var size = this.size
        while (i < size) {
            if (this[i].mLevel < level) {
                expandChildTree(i, this)
            }
            i++
            size = this.size
        }
    }

    private fun expandChildTree(position: Int, nodeList: ArrayList<Node>) {
        val node = nodeList[position]
        if (node.mChildrenList.isNullOrEmpty()) {
            return
        }
        val childrenList = node.mChildrenList
        //将所有的孩子添加到从position+1的位置开始的位置
        nodeList.addAll(position + 1, childrenList)
        //将此node的展开值设为true
        node.isExpand = true
    }
}

