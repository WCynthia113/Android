package com.example.treelayoutdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.treelayoutdemo.treeview.Node
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
        TreeAdapter(nodeList = nodeList,context = this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initAdapterData()
    }
    fun initRecyclerView(){
        treeAdapter.setOnclickListener(object: OnClickListener{
            override fun itemClicked(position: Int, node: Node) {
            }

            override fun itemStartIconClicked(position: Int, node: Node) {
                if (node.hasChild()&&node.mChildrenList.isEmpty()){
                    val newDataList = getNewData(node)
                    newDataList.forEach {
                        it.mParent = node
                    }
                    newDataList.setNodeParam()
                    node.mChildrenList.addAll(newDataList)
                }
            }

            override fun itemNameClicked(position: Int, node: Node) {

            }

            override fun itemEndIconClicked(position: Int, node: Node) {

            }

        })
        tree_view.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        tree_view.adapter = treeAdapter
    }
    fun initAdapterData(){
//        val task36513 = TaskModel(36513,
//            "【OA-测试计划】7月第2周",
//            "2020/07/06 09:00",
//            "2020/07/10 18:30",
//            0,
//            3,
//            "沈燕玲",
//            0,
//            3,
//            0,
//            19,
//            0,
//            0,
//            null,
//            -1,
//            "2020-07-06 20:54:55.0",
//            "###36013###36014###",
//            null,
//            arrayListOf(),
//            false
//        )

        val jsonString = "{\"id\":36013,\"name\":\"超级指令4.3.0版本按时发布\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":1,\"liableUsername\":\"陈昕\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":8,\"approval\":0,\"isPrivate\":0,\"icon\":\"face-4\",\"cycleNum\":-1,\"executeTime\":null,\"parentId\":null,\"tagList\":null,\"chlids\":[{\"id\":36014,\"name\":\"4.3.0版本测试执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":43,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":36513,\"name\":\"【OA-测试计划】7月第2周\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":19,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-06 20:54:55.0\",\"parentId\":\"###36013###36014###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":38219,\"name\":\"【OA-测试计划】7月第3周\",\"startTime\":\"2020/07/13 09:00\",\"endTime\":\"2020/07/18 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"沈燕玲\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":22,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36014###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false},{\"id\":36017,\"name\":\"平台组4.3.0研发执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"冯佳\",\"priority\":0,\"status\":1,\"flag\":0,\"subtaskNum\":48,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-03 21:19:41.0\",\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":36029,\"name\":\"【研发任务】第一轮\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/07/10 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"冯佳\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":22,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 15:27:52.0\",\"parentId\":\"###36013###36017###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":38307,\"name\":\"【研发任务】第二轮\",\"startTime\":\"2020/07/13 09:00\",\"endTime\":\"2020/07/18 18:30\",\"percent\":0,\"level\":3,\"liableUsername\":\"冯佳\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":24,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36017###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false},{\"id\":36022,\"name\":\"移动端4.3.0研发执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"刘明艺\",\"priority\":0,\"status\":0,\"flag\":0,\"subtaskNum\":1,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":37391,\"name\":\"7月第一周\",\"startTime\":\"2020/07/07 09:00\",\"endTime\":\"2020/07/10 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"刘明艺\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":28,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36022###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false},{\"id\":36024,\"name\":\"子产品4.3.0研发执行\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/08/28 18:38\",\"percent\":0,\"level\":2,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":1,\"flag\":0,\"subtaskNum\":47,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-03 00:11:33.0\",\"parentId\":\"###36013###\",\"tagList\":null,\"chlids\":[{\"id\":36071,\"name\":\"研发第一轮\",\"startTime\":\"2020/07/02 09:00\",\"endTime\":\"2020/07/04 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":7,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-06 09:59:33.0\",\"parentId\":\"###36013###36024###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":36082,\"name\":\"研发第二轮\",\"startTime\":\"2020/07/06 09:00\",\"endTime\":\"2020/07/10 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":2,\"flag\":0,\"subtaskNum\":18,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":\"2020-07-13 15:58:33.0\",\"parentId\":\"###36013###36024###\",\"tagList\":null,\"chlids\":[],\"today\":false},{\"id\":38345,\"name\":\"研发第三轮\",\"startTime\":\"2020/07/13 09:00\",\"endTime\":\"2020/07/17 18:38\",\"percent\":0,\"level\":3,\"liableUsername\":\"谢英亮\",\"priority\":0,\"status\":3,\"flag\":0,\"subtaskNum\":19,\"approval\":0,\"isPrivate\":0,\"icon\":null,\"cycleNum\":-1,\"executeTime\":null,\"parentId\":\"###36013###36024###\",\"tagList\":null,\"chlids\":[],\"today\":false}],\"today\":false}],\"today\":false}"
        val list = Gson().fromJson<TaskModel>(jsonString,TaskModel::class.java)
//        val task0 = TaskNode(this,0,"世界","","",1,2)
//        val task1 = TaskNode(this,1,"你好","","",0,2)
//        val task2 = TaskNode(this,2,"java","","",2,5)
//        val task3 = TaskNode(this,3,"kotlin","","",0,5)
//        val task4 = TaskNode(this,4,"python","","",1,5)
//        val task5 = TaskNode(this,5,"编程语言","","",3,7)
//        val task6 = TaskNode(this,6,"棒","","",0,4)
//        val task7 = TaskNode(this,7,"main","","",1,8)
//        nodeList.add(task0)
//        nodeList.add(task1)
//        nodeList.add(task2)
//        nodeList.add(task3)
//        nodeList.add(task4)
//        nodeList.add(task5)
//        nodeList.add(task6)
//        nodeList.add(task7)
//        nodeList.sortNode()
//        Log.e("list",nodeList.toString())
//        nodeList.setNodeParam()
        treeAdapter.notifyDataSetChanged()
    }

    fun getNewData(node: Node):List<Node>{
        val task9 = TaskNode(this,9,"快乐","","",0,0)
        return arrayListOf(task9)
    }
}