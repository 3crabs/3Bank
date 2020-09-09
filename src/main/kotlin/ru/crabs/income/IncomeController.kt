package ru.crabs.income

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/incomes")
class IncomeController {

    @Post
    fun addIncome(): IncomeGet? {
        return IncomeGet(1, 100)
    }
}
