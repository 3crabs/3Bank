package ru.crabs.income

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "incomes")
data class IncomeEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        var amount: Long,

        var created: Date
) {
    constructor() : this(0, 0, Date())
}
