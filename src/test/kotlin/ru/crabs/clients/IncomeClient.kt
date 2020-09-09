package ru.crabs.clients

import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client
import ru.crabs.income.IncomeCreate
import ru.crabs.income.IncomeGet

@Client("/incomes")
interface IncomeClient {

    @Post
    fun addIncome(income: IncomeCreate): IncomeGet
}
