package spartons.com.androidroomcoroutines

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import spartons.com.androidroomcoroutines.newTask.CreateNewTaskActivity
import spartons.com.androidroomcoroutines.roomPersistence.Task
import spartons.com.androidroomcoroutines.ui.AllTaskViewModel

class AllTaskActivity : AppCompatActivity(), AllTaskRecyclerViewAdapter.IDeleteTaskListener {

    private lateinit var viewModel: AllTaskViewModel

    private val taskAdapter: AllTaskRecyclerViewAdapter by lazy {
        AllTaskRecyclerViewAdapter(arrayListOf(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        val viewModelFactory = Injection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[AllTaskViewModel::class.java]
        allTaskRecyclerView.layoutManager = LinearLayoutManager(this)
        allTaskRecyclerView.adapter = taskAdapter
        viewModel.tasks
            .nonNull()
            .observe(this) {
                if (it.isEmpty())
                    noTaskImageView.visibility = View.VISIBLE
                else {
                    if (noTaskImageView.visibility == View.VISIBLE)
                        noTaskImageView.visibility = View.GONE
                    taskAdapter.updateTasks(it)
                }
            }
        createTaskButton.setOnClickListener {
            startActivity(Intent(this, CreateNewTaskActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.allTasks()
    }

    override fun onTaskDelete(task: Task) {
        viewModel.deleteTask(task)
    }
}
