import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toLong()}

	var max = 0L
	for(k in 0 until n){
		val (a,b,c) = f.readLine().split(" ").map{it.toLong()}
		max = max(max,a*b*c)
	}

	println(max-m)
}
