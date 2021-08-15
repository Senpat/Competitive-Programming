import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").toTypedArray()

	array[7] = "string"


}
