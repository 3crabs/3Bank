package ru.crabs.clients

import io.micronaut.http.client.annotation.Client
import ru.crabs.category.CategoryOperations

@Client("/categories")
interface CategoryClient : CategoryOperations
