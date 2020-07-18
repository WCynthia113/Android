package com.hp.goalgo.ui.main.treeview

import com.hp.common.model.entity.TaskBelongBean
import com.hp.core.widget.recycler.BaseRecyclerAdapter
import com.hp.core.widget.recycler.BaseRecyclerViewHolder
import com.hp.goalgo.model.entity.SearchData
import kotlinx.android.synthetic.main.item_search_data.view.*


class SearchAdapter(data: List<SearchData>? = null, val onClose: (TaskBelongBean?) -> Unit) : BaseRecyclerAdapter<SearchData,
        BaseRecyclerViewHolder>(com.hp.goalgo.R.layout.item_search_data, data) {

    override fun convert(holder: BaseRecyclerViewHolder?, itemData: SearchData?) {

        holder?.itemView?.apply {
            val sp = itemData?.deptchain ?: ""
            val str1 = sp.substring(0, sp.indexOf("###"))
            val dep = str1.split("@".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val dept = sp.split("###".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            // 内容
            idName.text = itemData?.username + "-" + dept[3]
            idCompare.text = itemData?.teamName+"-"+dept[1]
            holder.itemView.setOnClickListener {
                onClose(TaskBelongBean(
                        companyId = dep[1].toLong(),
                        companyName = null,
                        deptName = dept[1],
                        deptId = dep[2].toLong(),
                        userId = itemData?.id?.toLong(),
                        userName = itemData?.username ?: "",
                        belongType = 1))
            }


        }
    }
}

