package ru.crabs.category

import ru.crabs.Converter

object CategoryGetConverter : Converter<CategoryEntity, CategoryGet> {

    override fun convert(o: CategoryEntity): CategoryGet {
        return CategoryGet(o.id, o.name)
    }
}