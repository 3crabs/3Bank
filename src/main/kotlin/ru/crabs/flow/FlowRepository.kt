package ru.crabs.flow

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface FlowRepository : CrudRepository<FlowEntity, Long> {

    fun findOneById(id: Long): FlowEntity?

    fun findAllByType(type: String): List<FlowEntity>
}
