package ru.crabs

interface Converter<S, T> {

    fun convert(o: S): T
}
