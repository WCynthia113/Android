package com.example.treelayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.treelayoutdemo.treeview.Node
import com.example.treelayoutdemo.treeview.NodeHelper.setDefaultExpandLevel
import com.example.treelayoutdemo.treeview.NodeHelper.setNodeParam
import com.example.treelayoutdemo.treeview.TaskModel
import com.example.treelayoutdemo.treeview.TaskNode
import com.example.treelayoutdemo.treeview.TreeAdapter
import com.example.treelayoutdemo.treeview.TreeAdapter.OnClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val nodeList = arrayListOf<Node>()
    val treeAdapter by lazy {
        TreeAdapter(nodeList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initAdapterData()
    }

    fun initRecyclerView() {
        treeAdapter.setOnclickListener(object : OnClickListener {
            override fun itemClicked(position: Int, node: Node) {
            }

            override fun itemExpandIconClicked(position: Int, node: Node) {
                if (node.hasChild() && node.mChildrenList.isEmpty()) {
//                    if ((node as TaskNode).id == 36513)
                        getNewData(node as TaskNode)
                }
                if (node.isExpand) {
                    treeAdapter.collapseChildTree(position)
                } else {
                    treeAdapter.expandChildTree(position)
                }
                treeAdapter.notifyDataSetChanged()
            }

            override fun itemStartIconClicked(position: Int, node: Node) {
            }

            override fun itemNameClicked(position: Int, node: Node) {

            }

            override fun itemEndIconClicked(position: Int, node: Node) {

            }

        })
        tree_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tree_view.adapter = treeAdapter
    }

    fun initAdapterData() {
        val jsonString =
            "{\"id\":36013,\"name\":\"超级指令4.3.0版本按时发布\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":1,\"liableUsername\":\"陈昕\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":8,\"approval\":0,\"isPrivate\":0,\"icon\":\"face-4\",\"cycleNum\":-1,\"executeTime\":null,\"parentId\":null,\"tagList\":null,\"chlids\":[{\"id\":36014,\"name\":\"4.3.0版本测试执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":43,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":36513,\"name\":\"【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":19,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-06 20:54:55.0\",\"parentId\":\"###36013###36014###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":38219,\"name\":\"【OA-测试计划】7月第3周\",\"startTime\":\"2020/07/13 09:00\",\"endTime\":\"2020/07/18 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":22,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36014###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false},{\"id\":36017,\"name\":\"平台组4.3.0研发执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"冯佳\",\"priority\":0,\"status\":1,\"flag\":0,\"subtaskNum\":48,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-03 21:19:41.0\",\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":36029,\"name\":\"【研发任务】第一轮\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"冯佳\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":22,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 15:27:52.0\",\"parentId\":\"###36013###36017###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":38307,\"name\":\"【研发任务】第二轮\",\"startTime\":\"2020/07/13 09:00\",\"endTime\":\"2020/07/18 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"冯佳\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":24,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36017###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false},{\"id\":36022,\"name\":\"移动端4.3.0研发执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"刘明艺\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":1,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":37391,\"name\":\"7月第一周\",\"startTime\":\"2020/07/07 09:00\",\"endTime\":\"2020/07/10 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"刘明艺\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":28,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36022###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false},{\"id\":36024,\"name\":\"子产品4.3.0研发执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":1,\"flag\":0,\"subtaskNum\":47,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-03 00:11:33.0\",\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":36071,\"name\":\"研发第一轮\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/07/04 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":7,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-06 09:59:33.0\",\"parentId\":\"###36013###36024###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36082,\"name\":\"研发第二轮\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":18,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 15:58:33.0\",\"parentId\":\"###36013###36024###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":38345,\"name\":\"研发第三轮\",\"startTime\":\"2020/07/13 09:00\",\"endTime\":\"2020/07/17 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":19,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36024###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false}],\"today\":false}"
        val task = Gson().fromJson<TaskModel>(jsonString, TaskModel::class.java)
        val list = arrayListOf(task)
        nodeList.addAll(initTaskNode(list, null))
        nodeList.setNodeParam()
        nodeList.setDefaultExpandLevel(3)
        treeAdapter.notifyDataSetChanged()
    }

    fun getNewData(node: TaskNode){
        val jsonString =
            "{\"id\":36513,\"name\":\"【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":0,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36014###\",\"tagList\":null,\"chlids\":[{\"id\":36551,\"name\":\"【测试工程师】【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"吴颖\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":2,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 16:00:52.0\",\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36556,\"name\":\"【测试工程师】【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"蹇思帆\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":4,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-10 19:17:22.0\",\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36561,\"name\":\"【测试工程师】【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"魏潇\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":3,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 11:26:13.0\",\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36566,\"name\":\"【测试工程师】【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"张玲\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":2,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 10:56:51.0\",\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36571,\"name\":\"【测试工程师】【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"测试组-测试经理-沈燕玲\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":1,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36595,\"name\":\"审批评审\",\"startTime\":\"2020/07/08 14:00\",\"endTime\":\"2020/07/08 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":0,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 14:29:55.0\",\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36600,\"name\":\"工作台优化评审\",\"startTime\":\"2020/07/09 09:00\",\"endTime\":\"2020/07/09 18:30\",\"percent\":0,\"level\":4,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":0,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36014###36513###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false}"
        val task = Gson().fromJson<TaskModel>(jsonString, TaskModel::class.java)
        val list = initTaskNode(task.chlids,node)
        list.setNodeParam()
        node.mChildrenList.addAll(list)
    }

    fun initTaskNode(taskModelList: List<TaskModel>, parentNode: TaskNode?): List<TaskNode> {
        val taskNodeList = arrayListOf<TaskNode>()
        taskModelList.forEach {
            val parentId =
                if (it.parentId != null) {
                    val parentIdList = arrayListOf<String>()
                    parentIdList.addAll(it.parentId.split("###"))
                    parentIdList.removeAt(0)
                    parentIdList.removeAt(parentIdList.lastIndex)
                    parentIdList.last().toInt()
                } else null
            val taskNode =
                TaskNode(this, it.id, it.name, it.startTime, it.endTime, it.subtaskNum, parentId)
            taskNode.mParent = parentNode
            if (!it.chlids.isNullOrEmpty()) {
                val childList = initTaskNode(it.chlids, taskNode)
                taskNode.mChildrenList.addAll(childList)
            }
            taskNodeList.add(taskNode)
        }
        return taskNodeList
    }
}