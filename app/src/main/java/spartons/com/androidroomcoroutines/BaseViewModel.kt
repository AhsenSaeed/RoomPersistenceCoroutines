package spartons.com.androidroomcoroutines

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext


/**
 * Ahsen Saeed
 * ahsansaeed067@gmail.com
 * 4/6/19
 */

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}