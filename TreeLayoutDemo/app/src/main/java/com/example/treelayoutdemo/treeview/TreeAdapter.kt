package com.example.treelayoutdemo.treeview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.treelayoutdemo.DottedLineView
import com.example.treelayoutdemo.R

/**
 * Created by Wenxi on 2020/7/20 09:14
 */
class TreeAdapter(private var nodeList: ArrayList<Node>) :

    RecyclerView.Adapter<TreeAdapter.TreeVH>() {
    class TreeVH(view: View) : RecyclerView.ViewHolder(view) {
        val treeItem: LinearLayout = itemView.findViewById(R.id.tree_item)
        val expandView: ConstraintLayout = itemView.findViewById(R.id.expand_layout)
        val expandIcon: ImageView = itemView.findViewById(R.id.expand_icon)
        val bottomDashedLine: DottedLineView = itemView.findViewById(R.id.bottom_line)
        val startDashedLine: DottedLineView = itemView.findViewById(R.id.start_line)
        val centreDashedLine: DottedLineView = itemView.findViewById(R.id.centre_line)
        val ivStartIcon: ImageView = itemView.findViewById(R.id.iv_start_icon)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val ivEndIcon: ImageView = itemView.findViewById(R.id.iv_end_icon)
    }

    private var onClickListener: OnClickListener? = null
    private var currentSelectedNode: Node? = null //当前选择的Node
    fun setOnclickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeVH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.tree_listview_item, parent, false)
        return TreeVH(itemView)
    }

    override fun getItemCount(): Int {
        return nodeList.size
    }

    override fun onBindViewHolder(holder: TreeVH, position: Int) {
        val node = nodeList[position]
        //添加树的edge
        val treeEdge = node.getEdge()
        if (holder.itemView is ViewGroup) {
            val size = holder.itemView.childCount
            //移除之前的Edge视图
            for (i in 0 until size - 2) {
                holder.itemView.removeViewAt(0)
            }
            //因为每次是从0位置开始插入的，所以最后的edge应该最先插入，所以使用倒序插入
            treeEdge.reversed().forEach {
                val edgeView = LayoutInflater.from(holder.itemView.context)
                    .inflate(it, holder.itemView, false)
                holder.itemView.addView(edgeView, 0)
            }
        }
        //设置展开，关闭的图标
        if (node.isExpand) {
            holder.expandIcon.setImageResource(R.drawable.task_ic_close_list)
        } else {
            holder.expandIcon.setImageResource(R.drawable.task_ic_open_list)
        }
        //设置选中的背景
        if (node.isSelected) {
            holder.itemView.setBackgroundResource(R.drawable.tree_item_selected_background)
        } else {
            holder.itemView.setBackgroundResource(R.color.white)
        }
        //有children并在展开模式，设置下虚线
        holder.bottomDashedLine.visibility =
            if (node.isExpand && node.hasChild()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        //有parent，设置左虚线
        holder.startDashedLine.visibility =
            if (node.isRoot) {
                View.GONE
            } else {
                View.VISIBLE
            }
        //无孩子，隐藏扩展图标
        holder.centreDashedLine.visibility =
            if (!node.hasChild()) {
                holder.expandIcon.visibility = View.GONE
                View.VISIBLE
            } else {
                holder.expandIcon.visibility = View.VISIBLE
                View.GONE
            }
        //设置头部图标
        node.getStartIcon()?.let {
            holder.ivStartIcon.setImageResource(it)
            holder.ivStartIcon.visibility = View.VISIBLE
        }
        //设置尾部图标
        node.getEndIcon()?.let {
            holder.ivEndIcon.setImageResource(it)
            holder.ivEndIcon.visibility = View.VISIBLE
        }
        //设置文字
        holder.tvName.text = node.getName()

        //任意点击刷新界面（未避免层级太多，尾部显示不全，重刷界面，显示完整信息）
        holder.itemView.setOnClickListener {
            notifyDataSetChanged()
        }
        //设置item点击事件
        holder.treeItem.setOnClickListener {
            if (currentSelectedNode != node) {
                currentSelectedNode?.isSelected = false
                node.isSelected = true
                currentSelectedNode = node
            }
            notifyDataSetChanged()
        }
        //设置扩展图标的点击事件
        holder.expandView.setOnClickListener {
            onClickListener?.itemExpandIconClicked(position, node)
        }
    }

    /**
     * 展开，只展开当前点击的node的一层子树
     * @param position node在nodeList中的位置
     */
    fun expandChildTree(position: Int) {
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

    /**
     * 收起，要一次性将所有子树都收起来，所以需要递归
     * @param position node在nodeList中的位置
     */
    fun collapseChildTree(position: Int) {
        val node = nodeList[position]
        if (node.mChildrenList.isNullOrEmpty()) {
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
        fun itemExpandIconClicked(position: Int, node: Node)
        fun itemStartIconClicked(position: Int, node: Node)
        fun itemNameClicked(position: Int, node: Node)
        fun itemEndIconClicked(position: Int, node: Node)
    }

}