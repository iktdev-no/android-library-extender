package no.iktdev.extender.time

data class UnixTimestamp(var value: Long)

fun UnixTimestamp.reduceDays(days: Int) {
    this.value -= (days * 86400)
}

fun UnixTimestamp.reduceHours(hours: Int) {
    this.value -= (hours * 3600)
}