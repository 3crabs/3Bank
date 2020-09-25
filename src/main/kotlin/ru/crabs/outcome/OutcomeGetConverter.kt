package ru.crabs.outcome

import ru.crabs.base.Converter
import javax.inject.Singleton

@Singleton
class OutcomeGetConverter : Converter<OutcomeEntity, OutcomeGet> {

    override fun convert(o: OutcomeEntity): OutcomeGet {
        return OutcomeGet(o.id, o.amount, o.created)
    }
}
