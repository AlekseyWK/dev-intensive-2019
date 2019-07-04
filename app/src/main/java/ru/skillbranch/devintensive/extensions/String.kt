package ru.skillbranch.devintensive.extensions

fun String.truncate(len:Int=16):String= if (this.trimEnd().length>(len+1)) this.substring(0,len+1).trimEnd() + "..." else this.trimEnd()

fun String.stripHtml():String=this.replace(Regex("<[^<]*?>|&#\\d+;|&amp;|&quot;|&lt;|&qt;"),"")
    .replace(Regex("\\s+")," ")
