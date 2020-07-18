package com.example.drawlayoutdemo

import android.app.Activity
import android.app.Application
import android.content.res.Resources
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.drawlayoutdemo.treeview.Node
import com.hp.goalgo.ui.main.treeview.NodeTreeAdapter
import java.util.*

class FindAllEnterpriseTreeModel(application: Application) : ViewModel() {

//    private val findAllOnTemporiser by lazy {
//        FindAllEnterpriseTree()
//    }

    private var mRetract: Int = 0//层级过多追加的缩进值
    private var params: ViewGroup.LayoutParams? = null//层级过多，重新绘制视图
    private var mCompanyId: Long? = null
    private var mCompanyName: String? = null
    private var mBelongType: Int? = 0
    private var mDeptName: String? = null
    private var mDeptId: Long? = null
    private var mUserId: Long? = null
    private var mUserName: String? = null

    /**
     * teamId：企业id
     * id：当前选中层级的ID
     * */
    fun getOriginalDate(
        account: String,
        isAllOrg: Int,
        teamId: Any?,
        id: Any?,
        type: Int,
        isQueryAll: Int,
        userId: Long?,
        onSuccess: (DateSource?) -> Unit
    ) {

        //如果需要返回一个方法的话需要这么写， onSuccess: () -> Unit
        //object里有数组，或者是多个数组时候用：requestListBy，没得数组，一般用requestBy
//        if (teamId != null && id != null) {
//            findAllOnTemporiser.getOrangeDate(account, isAllOrg, teamId, id, type, isQueryAll, userId).requestBy(this, onNext = {
//                onSuccess(it)
//            }, showLoading = false)
//        }
    }


    fun getSearchDate(
        account: String,
        keywords: String,
        onlyUser: Int,
        orgIds: List<Long>?,
        onSuccess: (List<SearchData>?) -> Unit
    ) {
        //如果需要返回一个方法的话需要这么写， onSuccess: () -> Unit
        //object里有数组，或者是多个数组时候用：requestListBy，没得数组，一般用requestBy
//        if (orgIds != null) {
//            findAllOnTemporiser.getSearchDate(account, keywords, onlyUser, orgIds).requestBy(this, onNext = {
//                onSuccess(it)
//            })
//        }
    }

    /**
     * 工作台的组织架构
     */
    fun getWorkBenchDate(
        account: String,
        type: Int,
        userId: Long?,
        onSuccess: (ArrayList<DataList>?) -> Unit
    ) {
//        findAllOnTemporiser.getWorkBenchDate(account, type, userId).requestBy(this, onNext = {
//            onSuccess(it)
//        }, showLoading = false)
    }

    //点击-展开或收缩
    fun getClickToExpand(
        node: Node<*>, mAdapter: NodeTreeAdapter? = null,
        position: Int, mType: Int, mLinearLayout: LinearLayout,
        mActivity: Activity, mLinkedList: LinkedList<Node<*>>,
        mHorizontal: ScrollViewCustom, mListViewS: ListView
    ) {
        params = mLinearLayout.layoutParams

        if (node.ordinal != 0) {
            if (node.hasChild != 0) {
                if (node.hasChildList != 0) {
                    mAdapter?.expandOrCollapse(position, mType)
                    //如果缩回到三层以内，则重新绘制mLinearLayout和mListView的宽度
                    setView(mActivity, mLinearLayout)
                    if (node.mLevel < 3) {
                        //遍历显示出来的数据是否有超过三层数据(包含3层)
                        for (mLave in mLinkedList) {
                            //如果整个视图中有层级大于2级，则不对ListView重新绘制,反之，则重新绘制
                            if (mLave.mLevel >= 3) {
                                for (size in 2..mLave.mLevel) {
                                    mRetract += 16
                                }
                                break
                            } else {
                                mRetract = 0
                            }
                        }
                        setListViewSizeBaseChild(
                            mActivity.windowManager.defaultDisplay.width - 200 + mRetract,
                            mActivity,
                            mListViewS
                        )
                        mHorizontal.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//                        setListViewSizeBaseChilded(0,mActivity, mListViewS)
                    }
                } else {
                    if (!node.isExpand) {
                        mAdapter?.expandOrCollapse(position, mType)
                        //当点击的层级过多的时候重新绘制mLinearLayout和mListView的宽度

                        for (mLave in mLinkedList) {
                            //如果整个视图中有层级大于2级，则不对ListView重新绘制,反之，则重新绘制
                            if (mLave.mLevel >= 3) {
                                for (size in 2..mLave.mLevel) {
                                    mRetract += 16
                                }

                                break
                            } else {
                                mRetract = 0
                            }
                        }

                        setMoreView(mActivity, mLinearLayout, mRetract)
                        setListViewSizeBaseChild(
                            mActivity.windowManager.defaultDisplay.width - 200 + mRetract,
                            mActivity,
                            mListViewS
                        )
                        //定位到HorizontalScrollView最右侧
                        mHorizontal.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//                        setListViewSizeBaseChilded(0, mActivity,mListViewS)

                    } else {
                        mAdapter?.expandOrCollapse(position, mType)
                        //已动态加载过后，收起再次打开会走这一步
                        for (mLave in mLinkedList) {
                            //如果整个视图中有层级大于2级，则不对ListView重新绘制,反之，则重新绘制
                            if (mLave.mLevel > 3) {
                                for (size in 2..mLave.mLevel) {
                                    mRetract += 16
                                }
                                break
                            } else {
                                mRetract = 0
                            }
                            setView(mActivity, mLinearLayout)
                            setListViewSizeBaseChild(
                                mActivity.windowManager.defaultDisplay.width - 200 + mRetract,
                                mActivity,
                                mListViewS
                            )
                            mHorizontal.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//                            setListViewSizeBaseChilded(0, mActivity,mListViewS)
                        }
//                        setListViewSizeBaseChilded(0, mActivity,mListViewS)
                    }
                }
            }
        }
    }

    //点击展开更多
    fun getMoreToData(
        node: Node<*>, enterList: List<Long>? = null, mAdapter: NodeTreeAdapter? = null,
        position: Int, mType: Int, mLinearLayout: LinearLayout, mActivity: Activity,
        mHorizontal: ScrollViewCustom, mListViewS: ListView, account: String = ""
    ) {
        if (node.ordinal != 0) {//判断type是否是人
            if (node.hasChild != 0) {//判断是否有下级 0代表的是人
                val childNodeList = LinkedList<Node<*>>()
                var appendparentid: Long //departParent不为null时，在下面处理
                val appendicitis: Long = node.mId ?: 0 //当departParent为null时，则复用上一级的id
                val bigIds: String? = node.mBigId ?: ""//企业ID
                var bigId: String?
                var enterPrise: Long? = node.mParentId//--处理企业ID,
                //kotlin遍历  获取当前点击层级的企业ID
                enterList?.forEach { it1 ->
                    if (node.mBigId.toString().contains("#$it1#")) {
                        enterPrise = it1
                    }
                }
//                getOriginalDate(account, 0, enterPrise, node.mId, 3, 0, com.hp.task.component.CCTool.getOrganizationMember().id) {
//                    it?.childs?.forEach { bean ->
//                        if (!bean.departParent.isNullOrEmpty()) {
//                            val index: Int?
//                            val dep = bean.departParent.toString().split("#")
//                            index = if (dep.size == 3) {
//                                dep.size - 2
//                            } else {
//                                dep.size - 3
//                            }
//                            appendparentid = dep[Math.abs(index)].toLong()//当前节点的ID
//                            bigId = bean.departParent.toString()//获取当前点击所在的企业ID
//                        } else {
//                            appendparentid = appendicitis//当前节点的ID
//                            bigId = bigIds//获取当前点击所在的企业ID
//                        }
//
//                        val d = Dept(bean.id, appendparentid, bean.name, bean.person, bean.type, bean.roleName, bean.hasChild, bean.childs?.size, bigId, bean.logo)
//                        d.mLevel = node.mLevel + 1
//                        childNodeList.add(d)
//                    }
//
//                    node.mChildrenList.addAll(NodeHelper.sortNodes(childNodeList))
//                    mAdapter?.notifyDataSetChanged()
//                    mAdapter?.expandOrCollapse(position, mType)
//                    //如果当展开到第三层后就需要重新绘制mLinearLayout和mListView的宽度
//                    if (node.mLevel > 2) {
//                        for (size in 3..node.mLevel) {
//                            mRetract += 16
//                        }
//                        setMoreView(mActivity, mLinearLayout, mRetract)
//                        setListViewSizeBaseChilded(mActivity.windowManager.defaultDisplay.width - 200 + mRetract, mActivity, mListViewS)
//                        //定位到HorizontalScrollView最右侧
//                        mHorizontal.fullScroll(HorizontalScrollView.FOCUS_RIGHT)
//                        mRetract = 0
//                    }
////                    setListViewSizeBaseChilded(0, mActivity,mListViewS)
//                }
            }
        }
    }

    /**
     * 根据item设置listView宽度和高度
     * @param
     * @return
     */

    fun setListViewSizeBaseChild(mWidthParams: Int, context: Activity, mListViewS: ListView) {
        val items = mListViewS.adapter.count
//        var maxItemWidth = 0
        var totalHeight = 0
        val heightParams: Int
        for (i in 0 until items) {
            val itemView = mListViewS.adapter.getView(i, null, mListViewS)
            itemView.measure(0, 0)
//            val itemWidth = itemView.measuredWidth
            totalHeight += itemView.measuredHeight
//            if (itemWidth > maxItemWidth) {
//                maxItemWidth = itemWidth
//            }
        }
        //动态定制listView的高度
        val margin = dpToPixel(325f).toInt()
        heightParams = if (totalHeight > context.windowManager.defaultDisplay.height - margin) {
            totalHeight + mListViewS.dividerHeight * (mListViewS.adapter.count - 1) + 18
        } else {
            context.windowManager.defaultDisplay.height - margin
        }
        val params = mListViewS.layoutParams
        if (mWidthParams != 0) {
            params.width = mWidthParams
        }
        params.height = heightParams
        mListViewS.layoutParams = params
    }

    /**
     * 公用重新设置视图
     */

    private fun setView(mActivity: Activity, mLinearLayout: LinearLayout) {
        params?.width = mActivity.windowManager.defaultDisplay.width - 200
        mLinearLayout.layoutParams = params
    }

    /**
     * 公用重新设置视图--层级过多重新绘制视图
     */

    private fun setMoreView(mActivity: Activity, mLinearLayout: LinearLayout, mRetract: Int) {
        params?.width = mActivity.windowManager.defaultDisplay.width - 200 + mRetract
        mLinearLayout.layoutParams = params
    }

    /**
     * 点击进入任务列表
     */
    fun workList(
        node: Node<*>,
        mLinkedList: LinkedList<Node<*>>,
        enterLists: List<Long>? = null,
        position: Int,
        fragments: MutableList<Fragment>
    ) {

        for (person in mLinkedList) { //遍历list集合中的数据
            person.setIsChecked(false)//全部设为未选中
        }
        node.setIsChecked(true)
        //遍历获取企业ID
        enterLists?.forEach { it1 ->
            if (node.mBigId.toString().contains("#$it1#")) {
                this.mCompanyId = it1
            }
        }
        if (node.mId.toString() == mLinkedList[position].mParentId.toString()) {
            //点击的企业
            mCompanyName = mLinkedList[position].mLabel.toString()
            mCompanyName = null
        }

        //根据后台的type返回值区别：企业，部门，人
        mBelongType = when (node.ordinal) {
            2 -> 2//企业
            3 -> 3//部门
            else -> 1//人
        }

        //企业id
        //企业名字
        //区别人企业2，部门3，人1
        when (mBelongType) {
            2 -> {
                mCompanyId = node.mId
                mCompanyName = node.mLabel
                mDeptName = null
                mDeptId = null
                mUserId = null
                mUserName = null
            }
            3 -> {
                mCompanyName = null
                // mCompanyId = node._parentId
                mDeptName = node.mLabel
                mDeptId = node.mId
                mUserId = null
                mUserName = null
            }
            1 -> {
                //用父ID遍历出父名字
                for (nodes in mLinkedList) { //遍历list集合中的数据
                    if (node.mParentId == nodes.mId) {
                        mDeptName = nodes.mLabel
                        break
                    }
                }
                mDeptId = node.mParentId
                mUserId = node.mId
                mUserName = node.mLabel + "(${node.roleName})"
            }
        }

        //组成TaskBelongBean对象传给TaskFragment界面：加载不同的任务列表
//
//        (fragments[1] as TaskFragment).changeFragment(TaskBelongBean(
//                companyId = mCompanyId,//企业id
//                companyName = mCompanyName,//企业名字
//                deptName = mDeptName,
//                deptId = mDeptId,
//                userId = mUserId,
//                userName = mUserName,
//                belongType = mBelongType//区别人企业2，部门3，人名1
//        ))
    }

    private fun dpToPixel(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            Resources.getSystem().displayMetrics
        )
    }
}