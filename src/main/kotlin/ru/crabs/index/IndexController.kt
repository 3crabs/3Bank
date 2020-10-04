package ru.crabs.index

import io.micronaut.http.annotation.Controller

@Controller("/")
class IndexController : IndexOperations {

    override fun getIndex(): String {
        return "?:)"
    }
}
