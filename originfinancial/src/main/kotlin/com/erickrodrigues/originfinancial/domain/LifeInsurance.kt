package com.erickrodrigues.originfinancial.domain

class LifeInsurance(baseScore: Int = 0): Insurance(baseScore) {
    override fun userDoesNotHaveIncome() {}

    override fun userDoesNotHaveVehicle() {}

    override fun userDoesNotHaveHouse() {}

    override fun userOver60() { isIneligible = true }

    override fun userUnder30() { score -= 2 }

    override fun userBetween30and40() { score -= 1 }

    override fun incomeAbove200() { score -= 1 }

    override fun houseMortgaged() {}

    override fun userHasDependents() { score += 1 }

    override fun userMarried() { score += 1 }

    override fun vehicleWasProducedInTheLast5Years() {}
}
