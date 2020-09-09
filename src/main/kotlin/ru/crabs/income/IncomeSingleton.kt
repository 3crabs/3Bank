package ru.crabs.income

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncomeSingleton : IncomeService {

    @Inject
    lateinit var incomeRepository: IncomeRepository

    override fun addIncome(incomeEntity: IncomeEntity): IncomeEntity {
        return incomeRepository.save(incomeEntity)
    }
}
