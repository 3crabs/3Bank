package ru.crabs.category

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategorySingleton : CategoryService {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun addCategory(categoryEntity: CategoryEntity): CategoryEntity {
        return categoryRepository.save(categoryEntity)
    }

    override fun getCategories(): List<CategoryEntity> {
        return categoryRepository.findAll().toList()
    }
}
