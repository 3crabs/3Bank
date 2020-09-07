package ru.crabs.category

import io.micronaut.http.client.annotation.Client

@Client("/categories")
interface CategoryClient : CategoryOperations
