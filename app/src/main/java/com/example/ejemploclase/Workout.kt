package com.example.ejemploclase

class Workout(
    private var name: String,
    private var author: String,
    private var score: Number,
    private var categoryName: String,
    private var isFav: Boolean
) {

    fun getName() : String{
        return name
    }
    fun getAuthor() : String{
        return author
    }
    fun getScore() : Number{
        return score
    }
    fun getCategoryName() : String {
        return categoryName
    }
    fun getIsFav() : Boolean {
        return isFav
    }
}