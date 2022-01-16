package com.weronika

import com.weronika.models.AccountTable
import com.weronika.plugins.registerAccountRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {

    //*** Tell Ktor how to serialize list of Account objects
    install(ContentNegotiation) {
        json()
    }

    //*** Make sure your API works with browser clients
    install(CORS) {
        anyHost()
    }

    Database.connect("jdbc:h2:./coffee_house_accounts", "org.h2.Driver")
    transaction {

        SchemaUtils.create(AccountTable)
    }

    registerAccountRoutes()
}
