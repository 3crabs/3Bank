package ru.crabs.income

import ru.crabs.flow.FlowEntity

interface IncomeService {

    fun addIncome(flowEntity: FlowEntity): FlowEntity
}
