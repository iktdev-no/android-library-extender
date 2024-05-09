package no.iktdev.extender

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@Suppress("MemberVisibilityCanBePrivate")
class corIO: Coroutines() {

    /**
     * Returns a new CoroutineScope with IO dispatcher and a Job, using the global CoroutinesExceptionHandler instance.
     * This scope is appropriate for IO-bound tasks, such as reading or writing to files, network communication or database access.
     *
     * @return a CoroutineScope instance with the IO dispatcher and a Job
     */
    val scope = CoroutineScope(Dispatchers.IO + Job() + handler)


    override fun <T> async(context: CoroutineContext, start: CoroutineStart, block: suspend CoroutineScope.() -> T): Deferred<T> {
        return scope.async { block() }
    }

    override fun <T> launch(context: CoroutineContext, start: CoroutineStart, block: suspend CoroutineScope.() -> T): Job {
        return scope.launch { block() }
    }
}