package com.example.treelayoutdemo.treeview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import java.util.*


class NodeTreeAdapter(private val context: Context, private val nodeLinkedList: LinkedList<Node>) :
    BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val retract: Int = (context.resources.displayMetrics.density * 10 + 0.5f).toInt()//缩进值
    private var currentNode: Node? = null//当前被点击的node
    private var mNode: Node? = null//上一个被点击的node

    /**
     * 展开或收缩用户点击的条目
     *
     * @param position
     */
    fun expandOrCollapse(position: Int, type: Int) {
        if (currentNode == null){
            return
        }
        val node = nodeLinkedList[position]
        if (node != null && !node.isLeaf) {
            if (node.isExpand) {//如果是展开的
                collapse(node, position)
                mNode = currentNode
            } else {
                if (mNode == null) {
                } else {
                    if (mNode != currentNode) {
                        for (i in 0 until node.mChildrenList.size) {
                            val node1 = node.mChildrenList[i]
                            node1.setIsChecked(false)
                            if (node1.mId?.toString() == mId?.toString()) {
                                mId = id
                            }
                        }
                    }
                }
                nodeLinkedList.addAll(position + 1, node.mChildrenList)
            }
            node.isExpand = !node.isExpand
            notifyDataSetChanged()
        }
    }

    /**
     * 递归收缩用户点击的条目
     * 因为此中实现思路是：当用户展开某一条时，就将该条对应的所有子节点加入到nodeLinkedList
     * ，同时控制缩进，当用户收缩某一条时，就将该条所对应的子节点全部删除，而当用户跨级缩进时
     * ，就需要递归缩进其所有的孩子节点，这样才能保持整个nodeLinkedList的正确性，同时这种实
     * 现方式避免了每次对所有数据进行处理然后插入到一个list，最后显示出来，当数据量一大，就会卡顿，
     * 所以这种只改变局部数据的方式性能大大提高。
     *
     * @param position
     */
    private fun collapse(node: Node, position: Int) {
        node.isExpand = false
        val nodes = node.mChildrenList
        for (i in 0 until nodes.size) {
            val child = nodes[i]
            if (child.isExpand) {
                collapse(child, position + 1)
            }
            nodeLinkedList.removeAt(position + 1)
        }
    }

    override fun getCount(): Int {
        return nodeLinkedList.size
    }

    override fun getItem(position: Int): Any {
        return nodeLinkedList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        val holder: ViewHolder

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tree_listview_item, null)
            holder = ViewHolder()
            holder.rlBackground = convertView?.findViewById(R.id.id_item)
            holder.imageView = convertView.findViewById(R.id.id_icon)
            holder.label = convertView.findViewById(R.id.idTree)
            holder.conforming = convertView.findViewById(R.id.iv_confirm)
            holder.liSelect = convertView.findViewById(R.id.liSelect)
            holder.ivIcon = convertView.findViewById(R.id.iv_icon)
            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }
        val node = nodeLinkedList[position]


        val builder = StringBuilder()
        builder.append("(").append(node.labelPerson).append("人)")

        if (node.ordinal != 0) {
            //企业和部门 显示为：HP(54人) 移动端组（15人）
            holder.label?.text = node.mLabel + builder
        } else {
            //人 显示为:XX -安卓岗位
            holder.label?.text = node.mLabel + "-" + node.roleName
        }
        if (node.icon == -1) {
            holder.conforming?.visibility = View.INVISIBLE
        } else {
            holder.conforming?.visibility = View.VISIBLE
            holder.conforming?.setImageResource(node.icon)
        }


        if (node.mLevel == 1) {
            if (node.mLogo != null && node.mLogo != "") {
                node.mLogo?.let { holder.ivIcon?.loadCircleImage(it) }
            } else {
                holder.ivIcon?.setImageResource(R.drawable.icon_default_person)
            }
        } else {
            //企业
            if (node.ordinal == 2 && node.hasChild == 0) {
                holder.ivIcon?.setImageResource(R.color.white)
            } else {
                holder.ivIcon?.setImageResource(R.mipmap.icon_bit_select_depment)
            }
            //部门
            if (node.ordinal == 3 && node.hasChild == 0) {
                holder.ivIcon?.setImageResource(R.color.white)
            } else {
                holder.ivIcon?.setImageResource(R.mipmap.icon_bit_select_depment)
            }
            //人
            if (node.ordinal == 0) {
                holder.ivIcon?.setImageResource(R.color.white)
            } else {
                holder.ivIcon?.setImageResource(R.mipmap.icon_bit_select_depment)
            }
        }


        holder.liSelect?.setOnClickListener { v ->
            this.mListen.poo(v, position)
        }


        holder.conforming?.setOnClickListener { v -> this.mListen.poo(v, position) }
        //   holder.rlBackground?.setBackgroundResource(R.color.white)
        //判断CheckBox的状态
        if (node.checked()) {
            id = node.mId
            holder.label?.setTextColor(context.getColor(R.color.color_2196F3))
            holder.imageView?.background = context.resources.getDrawable(R.drawable.icon_select)//选中
            //  holder.rlBackground?.setBackgroundResource(R.color.color_e9f2ff)
            when (node.ordinal) {
                2 -> {
                    if (node.mLogo != null && node.mLogo != "") {
                        node.mLogo?.let { holder.ivIcon?.loadCircleImage(it) }
                    } else {
                        holder.ivIcon?.setImageResource(R.drawable.icon_default_person)
                    }

                    if (node.hasChild != 0) {
                        if (node.isExpand) {
                            holder.conforming?.setImageResource(R.mipmap.icon_selecttop_collapse)
                        } else {
                            holder.conforming?.setImageResource(R.mipmap.icon_select_collapse)
                        }
                    } else {
                        holder.conforming?.setImageResource(R.color.white)

                    }
                }
                3 -> {
                    //  holder.ivIcon?.setImageResource(R.mipmap.icon_select_depment)
                    holder.ivIcon?.setImageResource(R.mipmap.icon_bit_select_depment)
                    if (node.hasChild != 0) {
                        if (node.isExpand) {
                            holder.conforming?.setImageResource(R.mipmap.icon_selecttop_collapse)
                        } else {
                            holder.conforming?.setImageResource(R.mipmap.icon_select_collapse)
                        }

                    } else {
                        holder.conforming?.setImageResource(R.color.white)
                    }
                }
                0 -> {
                    holder.ivIcon?.setImageResource(R.color.white)
                    holder.conforming?.setImageResource(R.color.white)
                    /* if (node.isExpand) {
                         holder.conforming?.setImageResource(R.color.white)
                     } else {
                         holder.conforming?.setImageResource(R.color.white)
                     }*/
                }
            }
        } else {
            when (node.ordinal) {
                2 -> {
                    if (node.mLogo != null && node.mLogo != "") {
                        node.mLogo?.let { holder.ivIcon?.loadCircleImage(it) }
                    } else {
                        holder.ivIcon?.setImageResource(R.drawable.icon_default_person)
                    }

                    if (node.hasChild != 0) {
                        if (node.isExpand) {
                            holder.conforming?.setImageResource(R.mipmap.expand)
                        } else {
                            holder.conforming?.setImageResource(R.mipmap.collapse)
                        }
                    } else {
                        holder.conforming?.setImageResource(R.color.white)

                    }
                }
                3 -> {
                    holder.ivIcon?.setImageResource(R.mipmap.icon_bit_select_depment)
                    if (node.hasChild != 0) {
                        if (node.isExpand) {
                            holder.conforming?.setImageResource(R.mipmap.expand)
                        } else {
                            holder.conforming?.setImageResource(R.mipmap.collapse)
                        }
                    } else {
                        holder.conforming?.setImageResource(R.color.white)
                    }
                }
                0 -> {
                    holder.ivIcon?.setImageResource(R.color.white)
                    holder.conforming?.setImageResource(R.color.white)
                    /*    if (node.isExpand) {
                            holder.conforming?.setImageResource(R.color.white)
                        } else {
                            holder.conforming?.setImageResource(R.color.white)
                        }*/
                }
            }
            holder.label?.setTextColor(context.resources.getColor(R.color.color_70707a))
            holder.imageView?.background =
                context.resources.getDrawable(R.drawable.icon_not_click)//未选中
            holder.rlBackground?.setBackgroundResource(R.color.white)
        }
        convertView?.setPadding(node.mLevel * retract, 5, 5, 5)
        return convertView
    }


    internal class ViewHolder {
        var imageView: AppCompatImageView? = null
        var label: TextView? = null
        var conforming: AppCompatImageView? = null
        var liSelect: LinearLayoutCompat? = null
        var ivIcon: AppCompatImageView? = null
        var rlBackground: RelativeLayout? = null

    }

    lateinit var mListen: Unit //接口可以延时加载

    fun setListener(listen: Unit) {
        this.mListen = listen
    }

    interface Unit {
        fun poo(view: View, mPortion: Int)
    }

    fun ImageView.loadCircleImage(url: String) {
        ImageLoader.loadCircleImage(context, url, this)
    }
}
