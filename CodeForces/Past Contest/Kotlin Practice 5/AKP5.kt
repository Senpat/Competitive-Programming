import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(k in 1..f.readLine().toInt()){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		println(a+b)
	}
}
