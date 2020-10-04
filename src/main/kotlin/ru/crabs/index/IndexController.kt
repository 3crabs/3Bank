package ru.crabs.index

import io.micronaut.http.annotation.Controller
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller("/")
@Secured(SecurityRule.IS_ANONYMOUS)
class IndexController : IndexOperations {

    override fun getIndex(): String {
        return "?:)"
    }
}
