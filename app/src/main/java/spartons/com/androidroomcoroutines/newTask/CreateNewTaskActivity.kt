package spartons.com.androidroomcoroutines.newTask

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_new_task.*
import spartons.com.androidroomcoroutines.Injection
import spartons.com.androidroomcoroutines.R
import spartons.com.androidroomcoroutines.roomPersistence.Task
import spartons.com.androidroomcoroutines.ui.ViewModelFactory

/**
 * Ahsen Saeed
 * ahsansaeed067@gmail.com
 * 4/6/19
 */

class CreateNewTaskActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CreateNewTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_new_task)
        newTaskActivityToolbar.title = resources.getString(R.string.empty)
        setSupportActionBar(newTaskActivityToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModelFactory = Injection.provideViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)[CreateNewTaskViewModel::class.java]
        addNewTaskButton.setOnClickListener {
            if (checkEditText()) {
                val title = addNewTaskTitleTextView.text.toString()
                val description = addNewTaskDescriptionTextView.text.toString()
                viewModel.addTask(Task(taskName = title, taskDescription = description))
                showToast("Task added successfully.")
                clearFields()
            }
        }
    }

    private fun clearFields() {
        addNewTaskTitleTextView.setText("")
        addNewTaskDescriptionTextView.setText("")
    }

    private fun showToast(content: String) = Toast.makeText(this, content, Toast.LENGTH_SHORT).show()

    private fun checkEditText(): Boolean {
        if (addNewTaskTitleTextView.text.toString().isEmpty()) {
            showToast("Title field cannot be empty!")
            return false
        } else if (addNewTaskDescriptionTextView.text.toString().isEmpty()) {
            showToast("Description field cannot be empty!")
            return false
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }
}
