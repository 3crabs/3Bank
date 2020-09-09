package ru.crabs.income

import ru.crabs.base.Converter
import javax.inject.Singleton

@Singleton
class IncomeGetConverter : Converter<IncomeEntity, IncomeGet> {

    override fun convert(o: IncomeEntity): IncomeGet {
        return IncomeGet(o.id, o.amount)
    }
}
