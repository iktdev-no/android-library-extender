package no.iktdev.extender.time

class UnixTime {
    companion object {
        fun now() = UnixTimestamp(System.currentTimeMillis() / 1000L)
    }
}