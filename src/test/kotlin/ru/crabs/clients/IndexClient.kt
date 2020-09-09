package ru.crabs.clients

import io.micronaut.http.client.annotation.Client
import ru.crabs.index.IndexOperations

@Client("/")
interface IndexClient : IndexOperations
