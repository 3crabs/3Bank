package ru.crabs.flow

import ru.crabs.category.CategoryNotFoundException
import ru.crabs.category.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlowCreateConverter {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    fun convert(o: FlowCreate, type: String): FlowEntity {
        if (o.categoryId != null && !categoryRepository.existsById(o.categoryId)) {
            throw CategoryNotFoundException()
        }
        return FlowEntity(0, o.amount, o.created, type, o.categoryId)
    }
}
