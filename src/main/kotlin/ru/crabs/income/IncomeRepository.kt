package ru.crabs.income

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface IncomeRepository : CrudRepository<IncomeEntity, Long> {

    fun findOneById(id: Long): IncomeEntity?
}
