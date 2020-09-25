package ru.crabs.outcome

import ru.crabs.base.Converter
import ru.crabs.category.CategoryNotFoundException
import ru.crabs.category.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OutcomeCreateConverter : Converter<OutcomeCreate, OutcomeEntity> {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun convert(o: OutcomeCreate): OutcomeEntity {
        if (o.categoryId != null && !categoryRepository.existsById(o.categoryId)) {
            throw CategoryNotFoundException()
        }
        return OutcomeEntity(0, o.amount, o.created, null)
    }
}
