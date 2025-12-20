package com.sparrow.model


import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Person(val name: String, val age: Int)

fun main() {

    val data = Person("Alice", 25)
    println("data = $data")
    val jsonString = Json.encodeToString(data)
    println(jsonString)
    val decodeString = Json.decodeFromString<Person>(jsonString)
    println("decodeString = $decodeString")

}