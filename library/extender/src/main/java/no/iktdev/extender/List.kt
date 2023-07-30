package no.iktdev.extender

fun <T> List<T>.after(item: T?): T? {
    val index = this.indexOf(item)
    if (index < 0) return this.firstOrNull()
    val nextItemIndex = index + 1
    return if (this.size - 1 >= nextItemIndex) this[nextItemIndex] else null
}

fun <T> List<T>.before(item: T?): T? {
    val index = this.indexOf(item)
    if (index < 0) return this.firstOrNull()
    val prevItemIndex = index - 1
    return if (prevItemIndex >= 0) this[prevItemIndex] else null
}

fun <T> List<T>.allAfter(item: T?): List<T> {
    val index = this.indexOf(item)
    if (index < 0) return this
    val nextItemIndex = index + 1
    return if (this.size - 1 >= nextItemIndex) this.subList(nextItemIndex, this.size) else emptyList()
}

fun <T> List<T>.allBefore(item: T?): List<T> {
    val index = this.indexOf(item)
    return if (index < 0) this else this.subList(0, index)
}
