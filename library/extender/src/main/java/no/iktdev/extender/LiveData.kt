package no.iktdev.extender

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnceAfterInitUntilNotNull(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object: Observer<T> {
        var firstLoad = true
        override fun onChanged(t: T?) {
            if (t != null) {
                observer.onChanged(t)
            }
            if (!firstLoad) {
                removeObserver(this)
            }
            firstLoad = false
        }
    })
}