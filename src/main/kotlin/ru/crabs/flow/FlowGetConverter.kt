package ru.crabs.flow

import javax.inject.Singleton

@Singleton
class FlowGetConverter {

    fun convert(o: FlowEntity): FlowGet {
        return FlowGet(o.id, o.amount, o.created)
    }
}
