package ru.crabs.category

import javax.inject.Singleton

@Singleton
class CategoryCreateConverter {

    fun convert(o: CategoryCreate): CategoryEntity {
        return convert(null, o)
    }

    fun convert(categoryId: Long?, o: CategoryCreate): CategoryEntity {
        return CategoryEntity(0, o.name!!, categoryId)
    }
}
