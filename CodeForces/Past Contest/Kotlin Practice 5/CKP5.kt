import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val sum = f.readLine().split(" ").map{it.toInt()}.toIntArray().sum()
		println((sum+n-1)/n)
	}
}
