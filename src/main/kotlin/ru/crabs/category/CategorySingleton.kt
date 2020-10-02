package ru.crabs.category

import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategorySingleton : CategoryService {

    private val log = LoggerFactory.getLogger(CategorySingleton::class.java)

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun addCategory(categoryEntity: CategoryEntity): CategoryEntity {
        if (categoryRepository.existsByName(categoryEntity.name)) {
            throw CategoryIsAlreadyInUseException()
        }
        log.info("add category")
        return categoryRepository.save(categoryEntity)
    }

    override fun getCategories(): List<CategoryEntity> {
        log.info("get categories")
        return categoryRepository.findAllByCategoryIdIsNull()
    }

    override fun deleteCategory(id: Long): CategoryEntity? {
        val c = categoryRepository.findOneById(id)
        c?.id?.let {
            log.info("delete category")
            categoryRepository.deleteById(it)
        }
        return c
    }

    override fun updateCategory(id: Long, categoryEntity: CategoryEntity): CategoryEntity? {
        val c = categoryRepository.findOneById(id)
        c?.name = categoryEntity.name
        c?.id?.let {
            log.info("update category")
            categoryRepository.deleteById(it)
        }
        return c
    }
}
