package no.iktdev.extender

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@Suppress("MemberVisibilityCanBePrivate")
class corDefault: Coroutines() {

    /**
     * Returns a new CoroutineScope with Default dispatcher and a Job, using the global CoroutinesExceptionHandler instance.
     * This scope is appropriate for CPU-bound tasks, such as sorting or filtering large data sets or performing complex calculations.
     *
     * @return a CoroutineScope instance with the Default dispatcher and a Job
     */
    val scope = CoroutineScope(Dispatchers.Default + Job() + handler)


    override fun <T> async(context: CoroutineContext, start: CoroutineStart, block: suspend CoroutineScope.() -> T): Deferred<T> {
        return scope.async { block() }
    }

    override fun <T> launch(context: CoroutineContext, start: CoroutineStart, block: suspend CoroutineScope.() -> T): Job {
        return scope.launch { block() }
    }


}