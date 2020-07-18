package com.hp.goalgo.ui.main.treeview

import com.hp.common.ext.loadUserInfo
import com.hp.core.ext.*
import com.hp.core.widget.recycler.BaseRecyclerAdapter
import com.hp.core.widget.recycler.BaseRecyclerViewHolder
import com.hp.goalgo.R
import com.hp.goalgo.common.manager.GoUserManager
import com.hp.goalgo.model.entity.DataList
import com.hp.task.component.CCTool
import kotlinx.android.synthetic.main.workbench_listview_item.view.*


/**
 * @author  fengjunxia
 * @date    2020/4/1
 * @since   1.0
 */
class WorkBenchAdapter(data: List<DataList>? = null, private val onClose: (id: Long?, name: String?, date: DataList?, workCount: Int, workBenchAdapter: WorkBenchAdapter?) -> Unit) : BaseRecyclerAdapter<DataList,
        BaseRecyclerViewHolder>(R.layout.workbench_listview_item, data) {
    val userInfo by lazy {
        GoUserManager.INSTANCE.getUserInfo()
    }

    override fun convert(holder: BaseRecyclerViewHolder?, itemData: DataList?) {
        holder?.itemView?.apply {
            tvWorkBench.text = itemData?.shortName
            itemData?.logo?.let { imgWorkBench?.loadCircleImage(it) }
            if (itemData?.logo.isNullOrBlank()) {
                imgWorkBench.setBackgroundResource(R.drawable.icon_default_person)
            }
            if (itemData?.isSelect == true) imgSite?.visible() else imgSite?.inVisible()
            tvWorkBench?.setTextColor(if (itemData?.isSelect == true) context.getColorRes(R.color.color_191f25) else context.getColorRes(R.color.color_9a9aa2))
            if (holder.adapterPosition == 0) {
                if (itemData?.profile != null && itemData?.profile != "") {
                    ivFirstHead.loadUserInfo(itemData?.profile, CCTool.getOrganizationMember().userName)
                } else {
                    ivFirstHead.loadUserInfo(userInfo?.profile, CCTool.getOrganizationMember().userName)
                }
                ivFirstHead.visible()
                imgWorkBench?.inVisible()
            } else {
                ivFirstHead.gone()
                imgWorkBench?.visible()
            }
            val count = itemData?.workbenchCount!! + itemData.communicationCount!!
            workBenchMsg?.text = (if (count > 99) "99+" else if (count == 0) "" else count).toString()

            if ((itemData.workbenchCount + itemData.communicationCount) == 0) workBenchMsg.inVisible() else workBenchMsg.visible()
            holder.itemView.setOnClickListener {
                itemData.isSelect = true
                val workCount = if ((itemData?.workbenchCount!! + itemData.communicationCount!!) == 0) 0 else itemData.workbenchCount
                onClose(itemData.id, itemData.name, itemData, workCount, this@WorkBenchAdapter)
                tvWorkBench?.setTextColor(context.getColorRes(R.color.color_191f25))
                imgSite?.visible()
            }
        }

    }

}
