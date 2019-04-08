package spartons.com.androidroomcoroutines

import android.content.Context
import spartons.com.androidroomcoroutines.roomPersistence.TaskDao
import spartons.com.androidroomcoroutines.roomPersistence.TaskDatabase


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/6/19}
 */

object Injection {

    private fun provideTaskDataSource(context: Context): TaskDao {
        val database = TaskDatabase.getInstance(context)
        return database.taskDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val taskDao = provideTaskDataSource(context)
        return ViewModelFactory(taskDao)
    }
}
