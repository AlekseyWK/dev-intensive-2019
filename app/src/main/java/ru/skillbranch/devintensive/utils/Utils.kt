package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder
import kotlin.math.absoluteValue

fun translitchar( char: Char, divider: String=" ")= when (char)
{
    'а'-> "a"
    'б'-> "b"
    'в'-> "v"
    'г'-> "g"
    'д'-> "d"
    'е'-> "e"
    'ё'-> "e"
    'ж'-> "zh"
    'з'-> "z"
    'и'-> "i"
    'й'-> "i"
    'к'-> "k"
    'л'-> "l"
    'м'-> "m"
    'н'-> "n"
    'о'-> "o"
    'п'-> "p"
    'р'-> "r"
    'с'-> "s"
    'т'-> "t"
    'у'-> "u"
    'ф'-> "f"
    'х'-> "h"
    'ц'-> "c"
    'ч'-> "ch"
    'ш'-> "sh"
    'щ'-> "sh'"
    'ъ'-> ""
    'ы'-> "i"
    'ь'-> ""
    'э'-> "e"
    'ю'-> "yu"
    'я'-> "ya"
    ' '->divider
    else -> char.toString()
}



object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = (if(fullName?.trim()?.length==0) null else fullName)?.split(" ")
        val firstName = parts?.getOrNull(0)
        val secondName = parts?.getOrNull(1)

        return firstName to secondName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        //TODO("")
        var buf: StringBuilder = StringBuilder()
        for (char in payload)

            buf.append(if(char.isUpperCase()) translitchar(char.toLowerCase(), divider).toUpperCase()
                        else translitchar(char, divider))

        return buf.toString()
    }

    fun toInintials(firstName: String?, lastName: String?): String? {
        //TODO("")
        val textInitials =(if(firstName== null || firstName.trim().length==0) "" else firstName[0].toString() +
                if(lastName== null || lastName.trim().length==0) "" else lastName[0].toString()).toUpperCase()
        return  if(textInitials.length==0) null else textInitials
    }

    fun inclination(interval: Long, value1:String, value2:String, value5:String ): String {
        return when (interval.absoluteValue) {
            in 10..19 -> value5
            else -> when (interval % 10) {
                1L -> value1
                in 2..4 -> value2
                else -> value5
            }
        }
    }

}