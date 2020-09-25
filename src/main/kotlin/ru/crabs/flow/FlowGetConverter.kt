package ru.crabs.flow

import ru.crabs.base.Converter
import javax.inject.Singleton

@Singleton
class FlowGetConverter : Converter<FlowEntity, FlowGet> {

    override fun convert(o: FlowEntity): FlowGet {
        return FlowGet(o.id, o.amount, o.created)
    }
}
