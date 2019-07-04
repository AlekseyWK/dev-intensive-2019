package ru.skillbranch.devintensive.extensions

fun String.truncate(len:Int=16):String= if (this.trimEnd().length>len) this.substring(0,len).trimEnd() + "..." else this.trimEnd()

fun String.stripHtml():String=this.replace(Regex("<[^<]*?>|&#\\d+;|&amp;|&quot;|&lt;|&qt;"),"")
    .replace(Regex("\\s+")," ")
