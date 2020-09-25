package ru.crabs.clients

import io.micronaut.http.client.annotation.Client
import ru.crabs.flow.FlowOperations

@Client("/outcomes")
interface OutcomeClient : FlowOperations
