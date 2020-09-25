package ru.crabs.income

import ru.crabs.flow.FlowEntity
import ru.crabs.flow.FlowRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncomeSingleton : IncomeService {

    @Inject
    lateinit var flowRepository: FlowRepository

    override fun addIncome(flowEntity: FlowEntity): FlowEntity {
        return flowRepository.save(flowEntity)
    }
}
