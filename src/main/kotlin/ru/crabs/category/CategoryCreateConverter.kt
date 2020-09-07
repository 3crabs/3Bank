package ru.crabs.category

import ru.crabs.Converter

object CategoryCreateConverter : Converter<CategoryCreate, CategoryEntity> {

    override fun convert(o: CategoryCreate): CategoryEntity {
        return CategoryEntity(0, o.name!!)
    }
}