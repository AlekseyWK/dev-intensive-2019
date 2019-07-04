package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder
import kotlin.math.absoluteValue

fun translitchar( char: Char, divider: String=" ")= when (char)
{
    'а'-> "A"
    'б'-> "B"
    'в'-> "V"
    'г'-> "G"
    'д'-> "D"
    'е'-> "E"
    'ё'-> "E"
    'ж'-> "Zh"
    'з'-> "Z"
    'и'-> "I"
    'й'-> "I"
    'к'-> "K"
    'л'-> "L"
    'м'-> "M"
    'н'-> "N"
    'о'-> "O"
    'п'-> "P"
    'р'-> "R"
    'с'-> "S"
    'т'-> "T"
    'у'-> "U"
    'ф'-> "F"
    'х'-> "H"
    'ц'-> "C"
    'ч'-> "Ch"
    'ш'-> "Sh"
    'щ'-> "Sh'"
    'ъ'-> ""
    'ы'-> "I"
    'ь'-> ""
    'э'-> "E"
    'ю'-> "Yu"
    'я'-> "Ya"
    ' '->divider
    else -> char.toString().toUpperCase()

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
        val buf = StringBuilder()
        for (char in payload)

            buf.append(if(char.isUpperCase()) translitchar(char.toLowerCase(), divider)
                        else translitchar(char, divider).toLowerCase())

        return buf.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        //TODO("")
        val textInitials =((if(firstName== null || firstName.trim().length==0) "" else firstName.trim()[0].toString()) +
                (if(lastName== null || lastName.trim().length==0) "" else lastName.trim()[0].toString())).toUpperCase()
        return  if(textInitials.length==0) null else textInitials
    }

    fun inclination(interval: Long, value1:String, value2:String, value5:String ): String =
         when (interval.absoluteValue%100) {
            in 10..19 -> value5
            else -> when (interval % 10) {
                1L -> value1
                in 2..4 -> value2
                else -> value5
            }
        }

    fun inclination(interval: Int, value1:String, value2:String, value5:String ): String =
        inclination(interval.toLong(), value1, value2, value5 )
}