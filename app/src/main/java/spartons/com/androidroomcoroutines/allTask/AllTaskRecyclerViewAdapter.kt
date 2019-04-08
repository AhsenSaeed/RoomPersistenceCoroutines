package spartons.com.androidroomcoroutines.allTask

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.color
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import spartons.com.androidroomcoroutines.R
import spartons.com.androidroomcoroutines.roomPersistence.Task


/**
 * Ahsen Saeed}
 * ahsansaeed067@gmail.com}
 * 4/8/19}
 */

class AllTaskRecyclerViewAdapter(private val tasks: ArrayList<Task>, private val context: Context) :
    RecyclerView.Adapter<AllTaskRecyclerViewAdapter.MyViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    private val deleteTaskClickListener: IDeleteTaskListener = if (context is IDeleteTaskListener)
        context
    else throw IllegalArgumentException("Calling class does not implement ISingleBuzzReportClickListener")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            layoutInflater.inflate(
                R.layout.task_list_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = tasks[position]
        val taskName = task.taskName
        val spannableString = SpannableStringBuilder()
            .color(R.color.colorPrimaryText) {
                bold { append(taskName) }
            }.append(" : ")
            .append(task.taskDescription)
        holder.taskTitleDescriptionTextView.text = spannableString
        holder.setClickListener { _position ->
            deleteTaskClickListener.onTaskDelete(tasks[_position])
        }
    }

    fun updateTasks(tasks: List<Task>) {
        val diffResult = DiffUtil.calculateDiff(
            TaskDiffCallback(
                this.tasks,
                tasks
            )
        )
        this.tasks.clear()
        this.tasks.addAll(tasks)
        diffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var closure: (Int) -> Unit
        private val taskDeleteImageView: ImageView = itemView.findViewById(R.id.taskDeleteImageView)
        val taskTitleDescriptionTextView: TextView = itemView.findViewById(R.id.taskTitleDescriptionTextView)

        init {
            taskDeleteImageView.setOnClickListener {
                closure.invoke(adapterPosition)
            }
        }

        fun setClickListener(closure: (Int) -> Unit) {
            this.closure = closure
        }
    }

    interface IDeleteTaskListener {
        fun onTaskDelete(task: Task)
    }
}