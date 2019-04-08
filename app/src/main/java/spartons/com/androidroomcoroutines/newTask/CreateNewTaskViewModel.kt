package spartons.com.androidroomcoroutines.newTask

import kotlinx.coroutines.launch
import spartons.com.androidroomcoroutines.BaseViewModel
import spartons.com.androidroomcoroutines.roomPersistence.Task
import spartons.com.androidroomcoroutines.roomPersistence.TaskDao


/**
 * Ahsen Saeed
 * ahsansaeed067@gmail.com
 * 4/6/19
 */

class CreateNewTaskViewModel constructor(private val taskDao: TaskDao) : BaseViewModel() {

    fun addTask(task: Task) = launch(coroutineContext) {
        taskDao.insertTask(task)
    }
}