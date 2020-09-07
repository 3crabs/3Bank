package ru.crabs.index

import io.micronaut.http.client.annotation.Client

@Client("/")
interface IndexClient : IndexOperations
