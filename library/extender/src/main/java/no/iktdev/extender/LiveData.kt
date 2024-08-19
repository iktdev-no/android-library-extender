package no.iktdev.extender

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeOnceAfterInitUntilNotNull(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object: Observer<T?> {
        var firstLoad = true
        override fun onChanged(value: T?) {
            if (value != null) {
                observer.onChanged(value)
            }
            if (!firstLoad) {
                removeObserver(this)
            }
            firstLoad = false
        }
    })
}