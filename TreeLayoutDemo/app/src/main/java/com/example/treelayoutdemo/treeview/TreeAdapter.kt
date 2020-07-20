package com.example.treelayoutdemo.treeview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.treelayoutdemo.R

/**
 * Created by Wenxi on 2020/7/20 09:14
 */
class TreeAdapter(var nodeList: ArrayList<Node>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class TreeVH(view: View) : RecyclerView.ViewHolder(view)
    private var onClickListener: OnClickListener? = null
    private var currentSelectedNode: Node? = null //当前选择的Node
    fun setOnclickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.tree_listview_item, parent, false)
        return TreeVH(itemView)
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val node = nodeList[position]
        val treeEdge = node.getEdge()
        if (holder.itemView is ViewGroup){
            val size = (holder.itemView as ViewGroup).childCount
            Log.e("count",size.toString())
                for (i in 0 until size-3){
                    (holder.itemView as ViewGroup).removeViewAt(0)
                }
            treeEdge.forEach {
                val imageView = ImageView(holder.itemView.context)
                imageView.setImageResource(it)
                (holder.itemView as ViewGroup).addView(imageView,0)
            }
        }
        val ivStartIcon = holder.itemView.findViewById<ImageView>(R.id.iv_start_icon)
        val tvName = holder.itemView.findViewById<TextView>(R.id.tv_name)
        val ivEndIcon = holder.itemView.findViewById<ImageView>(R.id.iv_end_icon)
        ivStartIcon.setImageResource(node.getStartIcon())
        tvName.text = node.getName()
        ivEndIcon.setImageResource(node.getEndIcon())
        holder.itemView.setOnClickListener {
            if (currentSelectedNode != node) {
                currentSelectedNode?.isSelected = false
                currentSelectedNode = node
            }
            onClickListener?.itemClicked(position, node)
//            notifyDataSetChanged()
        }
        ivStartIcon.setOnClickListener {
            if (currentSelectedNode != node) {
                currentSelectedNode?.isSelected = false
                currentSelectedNode = node
            }
            onClickListener?.itemStartIconClicked(position, node)
            if (node.isExpand){
                collapseChildTree(position)
            }else{
                expandChildTree(position)
            }
            notifyDataSetChanged()
        }
        tvName.setOnClickListener {
            if (currentSelectedNode != node) {
                currentSelectedNode?.isSelected = false
                currentSelectedNode = node
            }
            onClickListener?.itemNameClicked(position, node)
//            notifyDataSetChanged()
        }
        ivEndIcon.setOnClickListener {
            if (currentSelectedNode != node) {
                currentSelectedNode?.isSelected = false
                currentSelectedNode = node
            }
            onClickListener?.itemEndIconClicked(position, node)
//            notifyDataSetChanged()
        }
    }

    /**
     * 展开，只展开当前点击的node的一层子树
     * @param position node在nodeList中的位置
     */
    fun expandChildTree(position: Int) {
        val node = nodeList[position]
        if (node.mChildrenList.isNullOrEmpty()){
            return
        }
        val childrenList = node.mChildrenList
        //将所有的孩子添加到从position+1的位置开始的位置
        nodeList.addAll(position + 1, childrenList)
        //将此node的展开值设为true
        node.isExpand = true
    }

    /**
     * 收起，要一次性将所有子树都收起来，所以需要递归
     * @param position node在nodeList中的位置
     */
    fun collapseChildTree(position: Int) {
        val node = nodeList[position]
        if (node.mChildrenList.isNullOrEmpty()){
            return
        }
        val childrenList = node.mChildrenList
        //遍历所有孩子
        for (i in 0 until childrenList.size) {
            val child = childrenList[i]
            //如果孩子是展开的，则进行收起操作
            if (child.isExpand) {
                collapseChildTree(position + 1)
            }
            //将孩子从list中移除
            nodeList.removeAt(position + 1)
        }
        //将此node的展开值设为false
        node.isExpand = false
    }

    interface OnClickListener {
        fun itemClicked(position: Int, node: Node)
        fun itemStartIconClicked(position: Int, node: Node)
        fun itemNameClicked(position: Int, node: Node)
        fun itemEndIconClicked(position: Int, node: Node)
    }

}