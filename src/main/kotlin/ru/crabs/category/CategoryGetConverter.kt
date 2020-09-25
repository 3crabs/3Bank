package ru.crabs.category

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryGetConverter {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    fun convert(o: CategoryEntity): CategoryGet {
        return CategoryGet(o.id, o.name, categoryRepository.findAllByCategoryId(o.id).map { convert(it) })
    }
}
