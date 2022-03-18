package com.erickrodrigues.originfinancial.domain

class DisabilityInsurance(baseScore: Int = 0): Insurance(baseScore) {
    override fun userDoesNotHaveIncome() { isIneligible = true }

    override fun userDoesNotHaveVehicle() {}

    override fun userDoesNotHaveHouse() {}

    override fun userOver60() { isIneligible = true }

    override fun userUnder30() { score -= 2 }

    override fun userBetween30and40() { score -= 1 }

    override fun incomeAbove200() { score -= 1 }

    override fun houseMortgaged() { score += 1 }

    override fun userHasDependents() { score += 1 }

    override fun userMarried() { score -= 1 }

    override fun vehicleWasProducedInTheLast5Years() {}
}
