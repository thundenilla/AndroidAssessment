package com.amalinamakhtar.assessment

data class RestaurantModel(var name: String, val status: String) : Comparable<WeekDay> {

    override fun compareTo(other: WeekDay): Int {
        return compareValuesBy(this, other.value, { it })
    }
}

