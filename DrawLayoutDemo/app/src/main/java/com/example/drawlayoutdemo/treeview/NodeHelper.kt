package com.hp.goalgo.ui.main.treeview


import com.example.drawlayoutdemo.treeview.Node
import com.hp.goalgo.R


import java.util.ArrayList

/**
 * Created by Fjx on 2019/10/10.
 *
 * @description 节点帮助类
 */
object NodeHelper {

    /**
     * 传入所有的要展示的节点数据
     * @param nodeList  返回值是所有的根节点
     * @return
     */
    fun sortNodes(nodeList: MutableList<Node<*>>): List<Node<*>> {
        var nodeList = nodeList

        val rootNodes = ArrayList<Node<*>>()
        val size = nodeList.size
        var m: Node<*>
        var n: Node<*>
        //两个for循环整理出所有数据之间的父子关系，最后会构造出一个森林（就是可能有多棵树）
        for (i in 0 until size) {
            m = nodeList[i]
            if (m.hasChild != 0) {
                for (j in i + 1 until size) {
                    n = nodeList[j]
                    if (m.parent(n)) {
                        m.mChildrenList.add(n)
                        n.mParent = m
                    } else if (m.child(n)) {
                        n.mChildrenList.add(m)
                        m.mParent = n
                    }
                }
            }


        }
        //找出所有的树根，同事设置相应的图标（后面你会发现其实这里的主要
        // 作用是设置叶节点和非叶节点的图标）
        for (i in 0 until size) {
            m = nodeList[i]
            if (m.isRoot) {
                rootNodes.add(m)
            }

            for (j in i + 1 until size) {
                n = nodeList[j]
                setNodeIcon(n)
                //  setNodeLeftIcon(n)
            }
            if (m.hasChild != 0) {
                setNodeIcon(m)
                // setNodeLeftIcon(m)
            }
        }
        nodeList.clear()//此时所有的关系已经整理完成了
        // ，森林构造完成，可以清空之前的数据，释放内存，提高性能
        // ，如果之前的数据还有用的话就不清空
        nodeList = rootNodes//返回所有的根节点
        //  rootNodes = null;
        return nodeList
    }


    /**
     * 设置图标
     * @param node
     */
    private fun setNodeIcon(node: Node<*>) {
        // if (!node.isLeaf){
        if (node.labelPerson != 0) {
            if (node.isExpand) {
                node.icon = R.mipmap.expand

            } else {
                node.icon = R.mipmap.collapse

            }
        }
    }/*else{
            node.icon=-1
        }
    }*/

}

