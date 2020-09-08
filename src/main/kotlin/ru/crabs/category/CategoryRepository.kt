package ru.crabs.category

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface CategoryRepository : CrudRepository<CategoryEntity, Long> {

    fun findOneById(id: Long): CategoryEntity?

    fun findAllByCategoryId(categoryId: Long): List<CategoryEntity>

    fun findAllByCategoryIdIsNull(): List<CategoryEntity>
}
