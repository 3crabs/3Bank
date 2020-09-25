package ru.crabs.outcome

import ru.crabs.base.Converter
import ru.crabs.flow.FlowGet
import javax.inject.Singleton

@Singleton
class OutcomeGetConverter : Converter<OutcomeEntity, FlowGet> {

    override fun convert(o: OutcomeEntity): FlowGet {
        return FlowGet(o.id, o.amount, o.created)
    }
}
