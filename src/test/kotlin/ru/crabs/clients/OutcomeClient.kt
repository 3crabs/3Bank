package ru.crabs.clients

import io.micronaut.http.client.annotation.Client
import ru.crabs.flow.OutcomeOperations

@Client("/outcomes")
interface OutcomeClient : OutcomeOperations
