package ru.crabs.outcome

import ru.crabs.base.Converter
import ru.crabs.category.CategoryNotFoundException
import ru.crabs.category.CategoryRepository
import ru.crabs.flow.FlowCreate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OutcomeCreateConverter : Converter<FlowCreate, OutcomeEntity> {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun convert(o: FlowCreate): OutcomeEntity {
        if (o.categoryId != null && !categoryRepository.existsById(o.categoryId)) {
            throw CategoryNotFoundException()
        }
        return OutcomeEntity(0, o.amount, o.created, null)
    }
}
