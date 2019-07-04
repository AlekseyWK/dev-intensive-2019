package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils.inclination
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun TimeUnits.plural(value:Int): String=
    when (this) {
        TimeUnits.SECOND-> "$value ${inclination(value,"секунду","секунды","секунд")}"
        TimeUnits.MINUTE-> "$value ${inclination(value,"минуту","минуты","минут")}"
        TimeUnits.HOUR-> "$value ${inclination(value,"час","часа","часов")}"
        TimeUnits.DAY-> "$value ${inclination(value,"день","дня","дней")}"

    }


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String{
    val dateFormat= java.text.SimpleDateFormat(pattern, java.util.Locale("ru"))
    return dateFormat.format( this)
}

fun Date.add(value:Int, units: TimeUnits=TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}


fun Date.humanizeDiff(date: Date =Date()):String {
    val interval=(date.time - this.time)/ SECOND
 return   when (interval) {
     in -45..-1 -> "через несколько секунд"
     in -75..-45 -> "через минуту"
     in -45 * 60..-75 -> "через ${-interval / 60} ${inclination(-interval / 60, "минуту", "минуты", "минут")}"
     in -75 * 60..-45 * 60 -> "через час"
     in -22 * 60 * 60..-75 * 60 -> "через ${-interval / 60 / 60} ${inclination(
         -interval / 60 / 60,
         "час",
         "часа",
         "часов"
     )}"
     in -26 * 60 * 60..-22 * 60 * 60 -> "через день"
     in -360 * 60 * 60 * 24..-26 * 60 * 60 -> "через ${-interval / 60 / 60 / 24} ${inclination(
         -interval / 60 / 60 / 24,
         "день",
         "дня",
         "дней"
     )}"
     in -1..1 -> "только что"
     in 1..45 -> "несколько секунд назад"
     in 45..75 -> "минуту назад"
     in 75..45 * 60 -> "${interval / 60} ${inclination(interval / 60, "минуту", "минуты", "минут")} назад"
     in 45 * 60..75 * 60 -> "час назад"
     in 75 * 60..22 * 60 * 60 -> "${interval / 60 / 60} ${inclination(
         interval / 60 / 60,
         "час",
         "часа",
         "часов"
     )} назад"
     in 22 * 60 * 60..26 * 60 * 60 -> "день назад"
     in 26 * 60 * 60..360 * 60 * 60 * 24 -> "${interval / 60 / 60 / 24} ${inclination(
         interval / 60 / 60 / 24,
         "день",
         "дня",
         "дней"
     )} назад"
     else -> if (interval > 0) "более года назад" else "более чем через год"
 }
}