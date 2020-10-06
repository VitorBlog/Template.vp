package dev.vitorpaulo.template

import dev.vitorpaulo.template.util.RegexUtils
import java.io.File

class TemplateVP(constructorLabel: String, val model: HashMap<String, Any>) {

    private
    var finalHtml = ""
    var htmlString = ""

    init {

        val label = if (constructorLabel.endsWith(".vp")) constructorLabel else "$constructorLabel.vp"

        File(label)?.let {
            if (it.exists()) {

                htmlString = it.readText()

            }
        }
        this::class.java.classLoader.getResourceAsStream(label)?.let {

            htmlString = it.reader().readText()

        }

    }

    fun build(): String {

        for (line in htmlString.split("\n")) {

            if (!RegexUtils.has(line)) { finalHtml += line;continue }

            var string = line

            for (match in RegexUtils.allMatches(RegexUtils.matcher(line))) {

                string = string.replace(
                    match,
                    model[
                            match
                                .replace("{", "")
                                .replace("}", "")
                    ].toString() ?: "404"
                )

            }

            finalHtml += string

        }

        return finalHtml

    }

}