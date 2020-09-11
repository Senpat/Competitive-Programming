import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val a = f.readLine().split(" ").map{it.toLong()}
		val b = f.readLine().split(" ").map{it.toLong()}

		var mina = Long.MAX_VALUE
		var minb = Long.MAX_VALUE

		for(k in 0 until n){
			mina = min(mina,a[k])
			minb = min(minb,b[k])
		}

		var answer = 0L

		for(k in 0 until n){
			answer += max(a[k]-mina,b[k]-minb)
		}

		println(answer)
	}
}
