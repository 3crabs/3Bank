package ru.crabs.income

import io.micronaut.context.annotation.Requirements
import io.micronaut.context.annotation.Requires
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Produces
import io.micronaut.http.hateoas.JsonError
import io.micronaut.http.hateoas.Link
import io.micronaut.http.server.exceptions.ExceptionHandler
import javax.inject.Singleton

@Produces
@Singleton
@Requirements(Requires(classes = [CategoryNotFoundException::class, ExceptionHandler::class]))
class CategoryNotFoundExceptionHandler : ExceptionHandler<CategoryNotFoundException, HttpResponse<*>> {

    override fun handle(request: HttpRequest<*>, e: CategoryNotFoundException): HttpResponse<JsonError> {
        val error = JsonError("category not found").link(Link.SELF, Link.of(request.uri))
        return HttpResponse.badRequest(error)
    }
}
