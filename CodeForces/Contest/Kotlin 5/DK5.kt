import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val input = f.readLine().split(" ").map{it.toInt()}

		val array = Array<Pair<Int,Int>>(n){Pair(0,0)}
		for(k in 0 until n){
			array[k] = Pair(input[k],k+1)
		}

		array.sortWith(compareBy{it.first})

		var l = 0
		var r = n-1

		val answer = mutableListOf<Int>()

		var added = 0									//after last change
		while(l < r){
			if(added >= array[l].first){
				answer.add(array[l].second)
				added = 1
				l++
			} else {
				answer.add(array[r].second)
				added++
				r--
			}
		}

		answer.add(array[l].second)

		println(answer.joinToString(" "))
	}
}
