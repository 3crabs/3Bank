package ru.crabs.category

import javax.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var name: String
) {
    constructor() : this(0, "")
}
