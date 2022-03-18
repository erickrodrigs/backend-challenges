package com.erickrodrigues.originfinancial.domain

import java.time.LocalDate

data class House(val ownership_status: String)

data class Vehicle(val year: Int)

data class User(
    private val age: Int,
    private val dependents: Int,
    private val house: House?,
    private val income: Double,
    private val marital_status: String,
    private val risk_questions: List<Int>,
    private val vehicle: Vehicle?
) {
    val baseScore = risk_questions.reduce { sum, value -> sum + value }

    fun hasHouse() = house != null

    fun hasVehicle() = vehicle != null

    fun isOver(age: Int) = this.age > age

    fun isUnder(age: Int) = this.age < age

    fun isBetween(ageMin: Int, ageMax: Int) = age in ageMin..ageMax

    fun isIncomeAbove(value: Double) = income > value

    fun isHouseMortgaged() = house?.ownership_status == "mortgaged"

    fun hasDependents() = dependents > 0

    fun isMarried() = marital_status == "married"

    fun vehicleAge() = LocalDate.now().year - (vehicle?.year ?: 0)
}
