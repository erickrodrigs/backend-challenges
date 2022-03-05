package com.erickrodrigues.picpay.application.services

import com.erickrodrigues.picpay.domain.entities.PicPayUser
import com.erickrodrigues.picpay.domain.ports.NotifierService
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Component
class NotifierServiceImpl: NotifierService {

    override fun notify(payee: PicPayUser) {
        val url = "http://o4d9z.mocklab.io/notify"
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder(URI.create(url))
            .GET()
            .build()

        try {
            client.send(request, HttpResponse.BodyHandlers.ofString())
            println("finish notification")
        } catch (ex: Exception) { }
    }
}
