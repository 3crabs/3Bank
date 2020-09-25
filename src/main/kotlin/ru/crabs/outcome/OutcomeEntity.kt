package ru.crabs.outcome

import ru.crabs.category.CategoryEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "outcomes")
data class OutcomeEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        var amount: Long,

        var created: Date,

        @ManyToOne
        @JoinColumn(name = "category_id")
        val category: CategoryEntity?
) {
    constructor() : this(0, 0, Date(), null)
}
