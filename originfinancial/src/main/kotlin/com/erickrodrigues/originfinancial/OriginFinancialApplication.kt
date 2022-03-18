package com.erickrodrigues.originfinancial

import com.erickrodrigues.originfinancial.domain.CalculateRiskProfile
import com.erickrodrigues.originfinancial.domain.User
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/calculate-risk-profile")
@SpringBootApplication
class OriginFinancialApplication {

	@PostMapping
	fun calculateRisk(@RequestBody user: User) = CalculateRiskProfile.execute(user)
}

fun main(args: Array<String>) {
	runApplication<OriginFinancialApplication>(*args)
}
