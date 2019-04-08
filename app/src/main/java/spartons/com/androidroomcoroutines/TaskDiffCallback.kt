package spartons.com.androidroomcoroutines

import androidx.recyclerview.widget.DiffUtil
import spartons.com.androidroomcoroutines.roomPersistence.Task


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/8/19}
 */

class TaskDiffCallback(private val oldTaskList: List<Task>, private val newTaskList: List<Task>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTask = oldTaskList[oldItemPosition]
        val newTask = newTaskList[newItemPosition]
        return oldTask.id == newTask.id
    }

    override fun getOldListSize() = oldTaskList.size

    override fun getNewListSize() = newTaskList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTask = oldTaskList[oldItemPosition]
        val newTask = newTaskList[newItemPosition]
        return oldTask.taskName == newTask.taskName && oldTask.taskDescription == newTask.taskDescription
    }
}