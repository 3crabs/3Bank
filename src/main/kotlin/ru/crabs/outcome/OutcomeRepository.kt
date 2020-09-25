package ru.crabs.outcome

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface OutcomeRepository : CrudRepository<OutcomeEntity, Long> {

    fun findOneById(id: Long): OutcomeEntity?
}

