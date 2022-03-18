package com.erickrodrigues.originfinancial.domain

abstract class Insurance(protected var score: Int = 0) {
    protected var isIneligible = false

    abstract fun userDoesNotHaveIncome()
    abstract fun userDoesNotHaveVehicle()
    abstract fun userDoesNotHaveHouse()
    abstract fun userOver60()
    abstract fun userUnder30()
    abstract fun userBetween30and40()
    abstract fun incomeAbove200()
    abstract fun houseMortgaged()
    abstract fun userHasDependents()
    abstract fun userMarried()
    abstract fun vehicleWasProducedInTheLast5Years()

    fun getStatus() =
        if (isIneligible) "ineligible"
        else if (score <= 0) "economic"
        else if (score in 1..2) "regular"
        else "responsible"
}
