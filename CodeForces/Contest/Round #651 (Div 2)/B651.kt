import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toInt()}

		val answer = mutableListOf<Pair<Int,Int>>()

		var lastodd = -1
		var lasteven = -1

		for(k in 0 until 2*n){
			if(answer.size == n-1) break
			if(array[k]%2 == 0){
				if(lasteven == -1) lasteven = k
				else{
					answer.add(Pair(lasteven+1,k+1))
					lasteven = -1
				}
			} else {
				if(lastodd == -1) lastodd = k
				else{
					answer.add(Pair(lastodd+1,k+1))
					lastodd = -1
				}
			}
		}

		for(p in answer){
			println("${p.first} ${p.second}")
		}
	}
}
