package com.example.ejemploclase

class Filter(
    private var filter: String = "Top Rated",// TODO ver como se guarda los filtros y ponerlos en un enum
    private var title: String = "Top Rated"
){

    fun getFilter() : String{
        return filter
    }
    fun getTitle() : String{
        return title
    }
}