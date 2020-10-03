package ru.crabs

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme

@OpenAPIDefinition(
        info = Info(
                title = "PiggyBank",
                version = "1.8",
                description = "API для приложения piggy bank"
        )
)
@SecurityScheme(name = "basic", scheme = "basic", type = SecuritySchemeType.HTTP)
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.run(Application.javaClass)
    }
}
