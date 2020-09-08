package ru.crabs.category

import ru.crabs.Converter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryGetConverter : Converter<CategoryEntity, CategoryGet> {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun convert(o: CategoryEntity): CategoryGet {
        return CategoryGet(o.id, o.name, categoryRepository.findAllByCategoryId(o.id).map { convert(it) })
    }
}
