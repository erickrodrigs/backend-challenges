package com.erickrodrigues.originfinancial.domain

class CalculateRiskProfile {
    companion object {
        fun execute(user: User): RiskProfile {
            val autoInsurance = AutoInsurance(user.baseScore)
            val disabilityInsurance = DisabilityInsurance(user.baseScore)
            val homeInsurance = HomeInsurance(user.baseScore)
            val lifeInsurance = LifeInsurance(user.baseScore)

            listOf(autoInsurance, disabilityInsurance, homeInsurance, lifeInsurance)
                .forEach {
                    if (!user.isIncomeAbove(0.0)) it.userDoesNotHaveIncome()
                    if (!user.hasHouse()) it.userDoesNotHaveHouse()
                    if (!user.hasVehicle()) it.userDoesNotHaveVehicle()
                    if (user.isOver(60)) it.userOver60()
                    if (user.isUnder(30)) it.userUnder30()
                    if (user.isBetween(30, 40)) it.userBetween30and40()
                    if (user.isIncomeAbove(200000.0)) it.incomeAbove200()
                    if (user.isHouseMortgaged()) it.houseMortgaged()
                    if (user.hasDependents()) it.userHasDependents()
                    if (user.isMarried()) it.userMarried()
                    if (user.vehicleAge() <= 5) it.vehicleWasProducedInTheLast5Years()
                }

            return RiskProfile(
                auto = autoInsurance.getStatus(),
                disability = disabilityInsurance.getStatus(),
                home = homeInsurance.getStatus(),
                life = lifeInsurance.getStatus()
            )
        }
    }
}
