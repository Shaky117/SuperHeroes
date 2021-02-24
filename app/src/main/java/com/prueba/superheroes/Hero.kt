package com.prueba.superheroes

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Hero(val results: ArrayList<Heroes>)

data class Heroes(
    val id: String,
    val name: String,
    val powerstats: PowerStats,
    val biography: Biography,
    val appearance: Appearance,
    val work : Work,
    val connections : Connections,
    val image : Image
) : Serializable

data class PowerStats(
    val intelligence: String,
    val strength : String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
) : Serializable

data class Biography(
    @SerializedName("full-name")
    val fullName: String,
    @SerializedName("alter-egos")
    val alterEgo: String,
    val aliases: ArrayList<String>,
    @SerializedName("place-of-birth")
    val placeOfBirth: String,
    @SerializedName("first-appearance")
    val firstAppearance: String,
    @SerializedName("publisher")
    val publisher: String,
    val alignment: String
) : Serializable

data class Appearance(
    val gender: String,
    val race: String,
    val height : ArrayList<String>,
    val weigth : ArrayList<String>,
    @SerializedName("eye-color")
    val eyeColor : String,
    @SerializedName("hair-color")
    val hairColor : String
) : Serializable

data class Work(
    val occupation : String,
    val base : String
) : Serializable

data class Connections(
    @SerializedName("group-affiliation")
    val affiliations: String,
    val relatives: String
) : Serializable

data class Image(
    val url : String
) : Serializable