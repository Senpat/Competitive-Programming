import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (a0,a1,a2) = f.readLine().split(" ").map{it.toInt()}
		val (b0,b1,b2) = f.readLine().split(" ").map{it.toInt()}

		val plus2 = min(a2,b1)
		val remaininga2 = a2-plus2

		val minus2 = max(0,b2-(remaininga2+a0))

		val answer = 2*(plus2-minus2)

		println(answer)
	}
}
