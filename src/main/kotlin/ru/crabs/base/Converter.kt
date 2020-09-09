package ru.crabs.base

interface Converter<S, T> {

    fun convert(o: S): T
}
