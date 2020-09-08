package ru.crabs.category

import javax.persistence.*

@Entity
@Table(name = "categories")
data class CategoryEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        var name: String,

        @Column(name = "category_id")
        var categoryId: Long?
) {
    constructor() : this(0, "", null)
}
