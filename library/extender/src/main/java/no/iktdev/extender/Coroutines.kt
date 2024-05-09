package no.iktdev.extender
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

@Suppress("MemberVisibilityCanBePrivate")
abstract class Coroutines {
    companion object {
        private val _exception = MutableLiveData<Throwable>()
        val exception: LiveData<Throwable> = _exception
        val handler = CoroutinesExceptionHandler()
    }

    /**
     * A global exception handler that catches unhandled exceptions thrown in Coroutines and stores them in a LiveData instance.
     * The key of this handler is a CoroutineExceptionHandler.Key instance.
     */
    class CoroutinesExceptionHandler : CoroutineExceptionHandler {
        /**
         * The key of this CoroutineExceptionHandler instance.
         */
        override val key = CoroutineExceptionHandler.Key

        /**
         * Handles an uncaught exception thrown by a Coroutine by storing it in a LiveData instance.
         *
         * @param context the CoroutineContext of the failed Coroutine
         * @param exception the Throwable instance representing the exception that was thrown
         */
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            var cause: Throwable? = null
            var current = exception
            while (current.cause != null) {
                cause = current.cause
                current = current.cause!!
            }

            if (cause != null) {
                _exception.postValue(cause)
            } else {
                _exception.postValue(exception)
            }
        }
    }

    abstract fun <T> async(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T>

    abstract fun <T> launch(
        context: CoroutineContext,
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> T
    ): Job
}
