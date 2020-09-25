package ru.crabs.income

import ru.crabs.base.Converter
import ru.crabs.category.CategoryNotFoundException
import ru.crabs.category.CategoryRepository
import ru.crabs.flow.FlowCreate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncomeCreateConverter : Converter<FlowCreate, IncomeEntity> {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun convert(o: FlowCreate): IncomeEntity {
        if (o.categoryId != null && !categoryRepository.existsById(o.categoryId)) {
            throw CategoryNotFoundException()
        }
        return IncomeEntity(0, o.amount, o.created, null)
    }
}
