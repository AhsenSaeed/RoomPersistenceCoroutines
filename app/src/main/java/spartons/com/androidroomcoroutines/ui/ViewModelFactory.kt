package spartons.com.androidroomcoroutines.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import spartons.com.androidroomcoroutines.newTask.CreateNewTaskViewModel
import spartons.com.androidroomcoroutines.roomPersistence.TaskDao

/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/6/19}
 */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AllTaskViewModel::class.java))
            return AllTaskViewModel(taskDao) as T
        else if(modelClass.isAssignableFrom(CreateNewTaskViewModel::class.java))
            return CreateNewTaskViewModel(taskDao) as T
        throw IllegalArgumentException("Unknown View Model class")
    }
}