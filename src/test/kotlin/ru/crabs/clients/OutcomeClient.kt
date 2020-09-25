package ru.crabs.clients

import io.micronaut.http.client.annotation.Client
import ru.crabs.outcome.OutcomeOperations

@Client("/outcomes")
interface OutcomeClient : OutcomeOperations
