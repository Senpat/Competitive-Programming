import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val array = Array<Pair<Int,Int>>{
		(a,b) = f.readLine().split(" ").map{it.toInt()}
		Pair(a,b)
	}

	if(n%2 == 1){
		//find min of first part
		var lower = Int.MAX_VALUE
		for(k in 0 until n/2){
			lower = min(lower,array[k].first)
		}
	}
}
