package com.weronika.plugins

import com.weronika.models.Account
import com.weronika.models.AccountTable
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.registerAccountRoutes() {
    routing { accountRouting() }
}

fun Route.accountRouting() {

    route("/account") {

        get {

            val accounts = transaction {
                AccountTable.selectAll().map {AccountTable.toAccount(it)}
            }
            call.respond(accounts)
        }

        get("/{email}") {

            val email = call.parameters["email"] ?: return@get call.respondText(
                "Missing or malformed email",
                status = HttpStatusCode.BadRequest
            )
            val account = transaction {
                AccountTable.select { AccountTable.email eq email }
                    .map { AccountTable.toAccount(it) }
            }
            call.respond(account)
        }

        post {
            val account = call.receive<Account>()
            transaction {
                AccountTable.insert {
                    it[firstName] = account.firstName
                    it[lastName] = account.lastName
                    it[email] = account.email
                    it[password] = account.password
                }
            }
            call.respond(account)
        }

        delete("/{email}") {
            val email = call.parameters["email"] ?: return@delete
                call.respond(HttpStatusCode.BadRequest)

            val account = transaction {
                AccountTable.deleteWhere { AccountTable.email eq email }
            }

            call.respond(account)
        }
    }

}
