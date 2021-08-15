import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		var max = 0
		for(k in 0 until n){
			val (l,r) = f.readLine().split(" ").map{it.toInt()}

			if(m < l || m > r) continue

			max = max(max,r-m+1)
		}

		println(max)
	}
}
