package ru.crabs.category

import ru.crabs.Converter
import javax.inject.Singleton

@Singleton
class CategoryCreateConverter : Converter<CategoryCreate, CategoryEntity> {

    override fun convert(o: CategoryCreate): CategoryEntity {
        return convert(null, o)
    }

    fun convert(categoryId: Long?, o: CategoryCreate): CategoryEntity {
        return CategoryEntity(0, o.name!!, categoryId)
    }
}
