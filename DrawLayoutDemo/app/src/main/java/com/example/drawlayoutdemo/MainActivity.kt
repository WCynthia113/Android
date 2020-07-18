package com.example.drawlayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.hp.goalgo.ui.main.treeview.Dept
import com.example.drawlayoutdemo.treeview.Node
import com.hp.goalgo.ui.main.treeview.NodeHelper
import com.hp.goalgo.ui.main.treeview.NodeTreeAdapter
import kotlinx.android.synthetic.main.nav_menu_main.*
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val  viewModel by lazy {
        ViewModelProvider(this).get(FindAllEnterpriseTreeModel::class.java)
    }

    //开始：3.0.3版本的组织架构需要
    private var companyId: Long? = null
    private var data: ArrayList<Node<*>> = ArrayList()
    private var lastParentId: Long? = 0//若departParent为null，则当前节点使用上一层父节点
    private var enterLists: List<Long>? = null//每一层数组外面的id
    private var account: String = ""//账号
    private var searchDate: List<SearchData>? = null
    private var enterList: List<Long>? = null//最外层的企业的ID数组
    private val mLinkedList = LinkedList<Node<*>>()
    private var mAdapter: NodeTreeAdapter? = null
    private var mType: Int = 0//默认为0，若点击后则赋值为1，在expandOrCollapse里展开和收缩的方法里使用
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val navController = findNavController(R.id.nav_host_fragment)
//        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
    }
    private fun initDrawerLayout() {

        mAdapter = NodeTreeAdapter(this, mLinkedList)
        mListViewS?.adapter = mAdapter

        //点击进入任务列表
        mListViewS.setOnItemClickListener { _, _, position, _ ->
            //当Viewpager=1，代表在TaskFragment页面，点击进入到组织架构
//            if (container?.currentItem == 1) {
//                //获取列表中item空间上的值
//                ivTreed.setImageResource(R.drawable.icon_not_click)
//                tvTopName.setTextColor(ContextCompat.getColor(this, R.color.color_9a9aa2))
//                mType = 1
//                viewModel.workList(mLinkedList[position], mLinkedList, enterLists, position, fragments)
//                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//                    drawerLayout.closeDrawer(GravityCompat.START)
//                }
//                mAdapter?.notifyDataSetChanged()
//            }

        }

        //处理点击展开和获取下一层级数据并且处理m
        mAdapter?.setListener(object : NodeTreeAdapter.Unit {
            override fun poo(view: View, mPortion: Int) {
                val node = mLinkedList[mPortion]
                if (node.mChildrenList.isNotEmpty()) {
                    viewModel.getClickToExpand(node, mAdapter, mPortion, mType, mLinearLayout, this@MainActivity, mLinkedList, mHorizontal, mListViewS)
                } else {
                    //点击更多追加后面的数据
                    viewModel.getMoreToData(node, enterList, mAdapter, mPortion, mType, mLinearLayout, this@MainActivity, mHorizontal, mListViewS, "account")
                }
            }
        })

//        //强制弹出虚拟键盘
//        viewModel.popUp(mInputsearch, ivCancel, tvCancel)
//        //弹出虚拟键盘，回车键改成搜索键
//        mInputsearch?.setOnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                if (mInputsearch?.text.toString().isNotEmpty()) {
//                    mSearchable()
//                }
//            }
//            false
//        }

        /*水平滑动*/
        mHorizontal?.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                mHorizontal?.startScrollerTask()
            }
            return@setOnTouchListener false
        }
    }

    //任务的组织架构
    private fun getDateSource() {

        viewModel.getOriginalDate("account", 1, 0, 0, 0, 0, 4) {
            val list = it?.childs
            enterList = it?.orgIds
            enterLists = it?.orgIds

            tvTopName.text = "我的任务"
//            tvViewTaskName.loadUserInfo(userInfo?.profile, com.hp.task.component.CCTool.getOrganizationMember().userName)

            handleData(list)
        }
    }

    /*处理组织架构逻辑--*/
    private fun handleData(list: List<ChildsBean>?) {
        var tmpList: List<ChildsBean>?
        for (one in list.orEmpty()) {
            var parentid: Long?
            var bigId: String? = ""
            if (!one.departParent.isNullOrEmpty()) {
                var index: Int?
                //用于接口返回的父ID是这种叠加的：#244#，#244#251，#244#251#467#
                //故写下面算法计算出父ID,例如，第一层父ID:244，第二层父ID:251，第三层父ID:467
                val dep = one.departParent.toString().split("#")
                index = if (dep.size == 3) {
                    dep.size - 2
                } else {
                    dep.size - 3
                }
                parentid = dep[abs(index)].toLong()
                //存储父ID类似如:#244#251#467# 用于后续判断获取当前点击所在的企业ID
                bigId = one.departParent.toString()
            } else {
                //如果父ID为空则使用上一层的id
                parentid = lastParentId
            }
            data.add(Dept(one.id, parentid, one.name, one.person, one.type, one.roleName, one.hasChild, one.childs?.size, bigId, one.logo))
            if (one.childs?.size != 0 && one.hasChild != 0) {
                lastParentId = one.id
                tmpList = one.childs
                handleData(tmpList!!)
            }
        }
        mLinkedList.addAll(NodeHelper.sortNodes(data))
        //设置mListView的宽度
        viewModel.setListViewSizeBaseChild(this.windowManager.defaultDisplay.width - 200, this, mListViewS)
        mAdapter?.notifyDataSetChanged()

    }
}