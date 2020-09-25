package ru.crabs.income

import ru.crabs.base.Converter
import ru.crabs.flow.FlowGet
import javax.inject.Singleton

@Singleton
class IncomeGetConverter : Converter<IncomeEntity, FlowGet> {

    override fun convert(o: IncomeEntity): FlowGet {
        return FlowGet(o.id, o.amount, o.created)
    }
}
