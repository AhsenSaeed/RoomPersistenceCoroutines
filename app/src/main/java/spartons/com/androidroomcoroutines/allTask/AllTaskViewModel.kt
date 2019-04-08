package spartons.com.androidroomcoroutines.allTask

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import spartons.com.androidroomcoroutines.BaseViewModel
import spartons.com.androidroomcoroutines.NonNullMediatorLiveData
import spartons.com.androidroomcoroutines.roomPersistence.Task
import spartons.com.androidroomcoroutines.roomPersistence.TaskDao


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/6/19}
 */

class AllTaskViewModel constructor(private val taskDao: TaskDao) : BaseViewModel() {

    private val _mTasks = NonNullMediatorLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _mTasks

    fun allTasks() = launch(coroutineContext) {
        _mTasks.postValue(taskDao.tasks())
    }

    fun deleteTask(task: Task) = launch(coroutineContext){
        taskDao.deleteTask(task)
        allTasks()
    }
}