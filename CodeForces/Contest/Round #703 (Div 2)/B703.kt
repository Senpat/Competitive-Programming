import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val xs = Array<Pair<Long,Long>>(n){Pair(0,0)}
		val ys = Array<Pair<Long,Long>>(n){Pair(0,0)}

		for(k in 0 until n){
			val (a,b) = f.readLine().split(" ").map{it.toLong()}
			xs[k] = Pair(a,b)
			ys[k] = Pair(a,b)
		}

		xs.sortWith(compareBy{it.first})
		ys.sortWith(compareBy{it.second})

		if(n%2 == 1) {
			println(1)
		} else {
			val x = xs[n/2].first-xs[n/2-1].first+1
			val y = ys[n/2].second-ys[n/2-1].second+1

			println(x*y)
		}
	}
}
