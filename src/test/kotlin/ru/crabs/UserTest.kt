package ru.crabs

import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest
import javax.inject.Inject

@MicronautTest
class UserTest : BaseTest() {

    @Inject
    @field:Client("/users")
    lateinit var httpClient: HttpClient

    init {
        "test add user" {
            val email = "mail@mail.ru"
            val user = User(email)

            val newUser = userClient.addUser(user)

            newUser.shouldNotBeNull()
            newUser.id.shouldNotBeNull()
            newUser.email shouldBe email
        }
    }
}
