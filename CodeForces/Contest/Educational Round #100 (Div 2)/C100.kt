import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = Array<Pair<Int,Int>>(n){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			Pair(a,b)
		}

		var answer = 0

		var loc = 0
		var dest = array[0].second
		var good = true
		for(k in 0 until n-1){
			if(dest >= loc){
				if(array[k].second >= loc && array[k].second <= dest && array[k].second-loc <= array[k+1].first-array[k].first) answer++
				val dif = dest-loc
				if(dif <= array[k+1].first-array[k].first){
					loc = dest
					dest = array[k+1].second
				} else {
					loc = loc + array[k+1].first-array[k].first
				}
			} else {
				if(array[k].second <= loc && array[k].second >= dest && loc-array[k].second <= array[k+1].first-array[k].first) answer++
				val dif = loc-dest
				if(dif <= array[k+1].first-array[k].first){
					loc = dest
					dest = array[k+1].second
				} else {
					loc = loc - (array[k+1].first-array[k].first)
				}
			}
			//println("$loc $dest $good")
		}
		//figure out nth command
		if(dest >= loc){
			if(array[n-1].second >= loc && array[n-1].second <= dest) answer++
		} else{
			if(array[n-1].second <= loc && array[n-1].second >= dest) answer++
		}

		println(answer)
	}
}
