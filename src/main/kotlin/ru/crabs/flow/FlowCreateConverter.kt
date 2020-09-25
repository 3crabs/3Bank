package ru.crabs.flow

import ru.crabs.base.Converter
import ru.crabs.category.CategoryNotFoundException
import ru.crabs.category.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlowCreateConverter : Converter<FlowCreate, FlowEntity> {

    @Inject
    lateinit var categoryRepository: CategoryRepository

    override fun convert(o: FlowCreate): FlowEntity {
        if (o.categoryId != null && !categoryRepository.existsById(o.categoryId)) {
            throw CategoryNotFoundException()
        }
        return FlowEntity(0, o.amount, o.created, null)
    }
}
