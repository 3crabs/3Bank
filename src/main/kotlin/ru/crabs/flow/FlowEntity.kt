package ru.crabs.flow

import ru.crabs.category.CategoryEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "flows")
data class FlowEntity(

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
