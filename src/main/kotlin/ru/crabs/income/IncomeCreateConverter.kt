package ru.crabs.income

import ru.crabs.base.Converter
import javax.inject.Singleton

@Singleton
class IncomeCreateConverter : Converter<IncomeCreate, IncomeEntity> {

    override fun convert(o: IncomeCreate): IncomeEntity {
        return IncomeEntity(0, o.amount)
    }
}
