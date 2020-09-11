package ru.crabs.income

import ru.crabs.base.Converter
import ru.crabs.category.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncomeCreateConverter : Converter<IncomeCreate, IncomeEntity> {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun convert(o: IncomeCreate): IncomeEntity {
        if (o.categoryId != null && !categoryRepository.existsById(o.categoryId)) {
            throw CategoryNotFoundException()
        }
        return IncomeEntity(0, o.amount, o.created, null)
    }
}
