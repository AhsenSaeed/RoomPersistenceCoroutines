package spartons.com.androidroomcoroutines.roomPersistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/6/19}
 */

@Entity(tableName = "tasks")
class Task(
    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "task_name")
    val taskName: String,
    @ColumnInfo(name = "task_description")
    val taskDescription: String


) {
    override fun toString(): String {
        return "Task(id='$id', taskName='$taskName', taskDescription='$taskDescription')"
    }
}