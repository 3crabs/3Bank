package ru.crabs.outcome

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OutcomeSingleton : OutcomeService {

    @Inject
    lateinit var outcomeRepository: OutcomeRepository

    override fun addOutcome(outcomeEntity: OutcomeEntity): OutcomeEntity {
        return outcomeRepository.save(outcomeEntity)
    }
}
