import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,x,m) = f.readLine().split(" ").map{it.toInt()}

		val array = Array<Pair<Int,Int>>(m){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			Pair(a,b)
		}

		var l = x
		var r = x

		for(k in 0 until m){
			if(array[k].first <= r && array[k].second >= l){
				l = min(l,array[k].first)
				r = max(r,array[k].second)
			}
		}

		val answer = r-l+1
		println(answer)
	}
}
