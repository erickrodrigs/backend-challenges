package com.erickrodrigues.originfinancial.domain

class AutoInsurance(baseScore: Int = 0): Insurance(baseScore) {
    override fun userDoesNotHaveIncome() {}

    override fun userDoesNotHaveVehicle() { isIneligible = true }

    override fun userDoesNotHaveHouse() {}

    override fun userOver60() {}

    override fun userUnder30() { score -= 2 }

    override fun userBetween30and40() { score -= 1 }

    override fun incomeAbove200() { score -= 1 }

    override fun houseMortgaged() {}

    override fun userHasDependents() {}

    override fun userMarried() {}

    override fun vehicleWasProducedInTheLast5Years() { score += 1 }
}
