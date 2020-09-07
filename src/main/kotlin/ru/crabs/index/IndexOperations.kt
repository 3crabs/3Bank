package ru.crabs.index

import io.micronaut.http.annotation.Get

interface IndexOperations {

    @Get
    fun getIndex(): String
}