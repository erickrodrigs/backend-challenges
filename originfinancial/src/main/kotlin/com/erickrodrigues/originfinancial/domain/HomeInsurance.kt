package com.erickrodrigues.originfinancial.domain

class HomeInsurance(baseScore: Int = 0): Insurance(baseScore) {
    override fun userDoesNotHaveIncome() {}

    override fun userDoesNotHaveVehicle() {}

    override fun userDoesNotHaveHouse() { isIneligible = true }

    override fun userOver60() {}

    override fun userUnder30() { score -= 2 }

    override fun userBetween30and40() { score -= 1 }

    override fun incomeAbove200() { score -= 1 }

    override fun houseMortgaged() { score += 1 }

    override fun userHasDependents() {}

    override fun userMarried() {}

    override fun vehicleWasProducedInTheLast5Years() {}
}
