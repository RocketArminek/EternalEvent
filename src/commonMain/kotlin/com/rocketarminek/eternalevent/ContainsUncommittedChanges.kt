package com.rocketarminek.eternalevent

interface ContainsUncommittedChanges<T> {
    fun commit(): List<T>
}
