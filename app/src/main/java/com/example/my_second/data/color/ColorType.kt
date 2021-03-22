package com.example.my_second.data.color

import android.graphics.Color

object ColorType  {
    val berryRed = "#b8256f"
    var red = "#db4035"
    val orange = "#ff9933"
    var yellow = "#fad000"
    var oliveGreen = "#afb83b"
    val limeGreen = "#7ecc49"
    val green = "#299438"
    val mintGreen = "#6accbc"
    val teal = "#158fad"
    val skyBlue = "#14aaf5"
    val lightBlue = "#96c3eb"
    val blue = "#4073ff"
    val grape = "#884dff"
    val violet = "#af38eb"
    val lavender = "#eb96eb"
    val magenta = "#e05194"
    val salmon = "#ff8d85"
    val charcoal = "#808080"
    val grey = "#b8b8b8"
    val taupe = "#ccac93"

    //Добавить все существующие цвета
    fun getProjectColorType(colorId: Int?): Int {
        val color =  when (colorId) {
            30 -> berryRed //berry_red -> berryRed
            31 -> red
            32 -> orange
            33 -> yellow
            34 -> oliveGreen
            35 -> limeGreen
            36 -> green
            37 -> mintGreen
            38 -> teal
            39 -> skyBlue
            40 -> lightBlue
            41 -> blue
            42 -> grape
            43 -> violet
            44 -> lavender
            45 -> magenta
            46 -> salmon
            47 -> charcoal
            48 -> grey
            49 -> taupe

            else -> "#000"
        }
        return Color.parseColor(color)
    }
}