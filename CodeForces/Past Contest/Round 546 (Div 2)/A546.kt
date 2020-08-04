import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val array = Array<Pair<Int,Int>>(n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		Pair(a,b)
	}

	val x = f.readLine().toInt()

	for(k in 0 until n){
		if(x <= array[k].second){
			println("${n-k}")
			return
		}
	}
	
	println("0")
}
