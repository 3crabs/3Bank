package ru.crabs.clients

import io.micronaut.http.client.annotation.Client
import ru.crabs.flow.IncomeOperations

@Client("/incomes")
interface IncomeClient : IncomeOperations
