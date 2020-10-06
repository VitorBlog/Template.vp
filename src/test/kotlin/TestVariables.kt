import dev.vitorpaulo.template.TemplateVP
import java.io.File
import kotlin.random.Random

object TestVariables {

    @JvmStatic
    fun main(args: Array<String>) {

        println("Testing variables")

        val model = hashMapOf<String, Any>()

        model["string"] = "Test"
        model["int"] = 30
        model["boolean"] = false
        model["list"] = listOf("1", "2", "3")
        model["listModel"] = listOf(TestObject(name = "test1"), TestObject(name = "test2"), TestObject(name = "test3"))
        model["map"] = hashMapOf(Random.nextInt() to "test1", Random.nextInt() to "test2", Random.nextInt() to "test3")
        model["mapModel"] = hashMapOf(Random.nextInt() to TestObject(name = "test1"), Random.nextInt() to TestObject(name = "test2"), Random.nextInt() to TestObject(name = "test3"))

        val result = File("result.html")
        result.createNewFile()
        result.writeText(TemplateVP("variables", model).build())

    }

    class TestObject(val id: Int = Random.nextInt(), val name: String)

}