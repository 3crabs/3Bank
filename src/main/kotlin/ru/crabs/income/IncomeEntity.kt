package ru.crabs.income

import javax.persistence.*

@Entity
@Table(name = "incomes")
data class IncomeEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        var amount: Long
) {
    constructor() : this(0, 0)
}
