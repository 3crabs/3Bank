package ru.crabs.category

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategorySingleton : CategoryService {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun addCategory(categoryEntity: CategoryEntity): CategoryEntity {
        if (categoryRepository.existsByName(categoryEntity.name)) {
            throw CategoryIsAlreadyInUseException()
        }
        return categoryRepository.save(categoryEntity)
    }

    override fun getCategories(): List<CategoryEntity> {
        return categoryRepository.findAllByCategoryIdIsNull()
    }

    override fun deleteCategory(id: Long): CategoryEntity? {
        val c = categoryRepository.findOneById(id)
        c?.id?.let { categoryRepository.deleteById(it) }
        return c
    }

    override fun updateCategory(id: Long, categoryEntity: CategoryEntity): CategoryEntity? {
        val c = categoryRepository.findOneById(id)
        c?.name = categoryEntity.name
        c?.id?.let { categoryRepository.deleteById(it) }
        return c
    }
}
