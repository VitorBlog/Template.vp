package dev.vitorpaulo.template.util

import java.util.regex.Matcher
import java.util.regex.Pattern

object RegexUtils {

    private
    val pattern = Pattern.compile("\\{(.*?)\\}")

    fun matcher(string: String) = pattern.matcher(string)

    fun allMatches(matcher: Matcher): ArrayList<String> {
        val arrayList = arrayListOf<String>()

        while (matcher.find()) {
            arrayList.add(matcher.group())
        }

        return arrayList
    }

    fun has(string: String) = allMatches(matcher(string)).isNotEmpty()

}