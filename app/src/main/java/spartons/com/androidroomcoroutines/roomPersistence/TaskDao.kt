package spartons.com.androidroomcoroutines.roomPersistence

import androidx.room.*

/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/6/19}
 */

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(t: Task): Long

    @Query(value = "SELECT * FROM tasks")
    suspend fun tasks(): List<Task>

    @Update
    suspend fun updateTask(t: Task)

    @Delete
    suspend fun deleteTask(t: Task)
}
