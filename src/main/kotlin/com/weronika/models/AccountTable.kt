package com.weronika.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object AccountTable : Table() {

    val firstName: Column<String> = varchar("firstName", 50)
    val lastName: Column<String> = varchar("lastName", 50)
    val email: Column<String> = varchar("email", 50)
    val password: Column<String> = varchar("password", 50)

    override val primaryKey = PrimaryKey(email)

    fun toAccount(row: ResultRow) : Account {

        return Account(
            firstName = row[AccountTable.firstName],
            lastName = row[AccountTable.lastName],
            email = row[AccountTable.email],
            password = row[AccountTable.password]
        )
    }
 }