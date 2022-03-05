package com.erickrodrigues.picpay.application.services

import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.ports.AuthorizerService
import org.bson.json.JsonObject
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class AuthorizerServiceImpl: AuthorizerService {

    override fun authorize(payer: PicPayUser): Boolean {
        val url = "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6"
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder(URI.create(url))
            .GET()
            .build()

        return try {
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            val json = JsonObject(response.body())
            val message = json.toBsonDocument()["message"]?.asString()!!.value
            message.equals("Autorizado", ignoreCase = true)
        } catch (ex: Exception) {
            false
        }
    }
}
