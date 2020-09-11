import java.io.*
import java.util.*
import kotlin.math.*
//lockout, WRONG
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	val MAX = 100000
	val BIG = 1000000000L
	val cost = LongArray(MAX+5){0}

	for(k in 0 until n){
		cost[array[k]] += BIG
		var i = array[k]*2
		var steps = 1L
		while(i <= MAX){
			cost[i] += BIG+steps
			i *= 2
			steps++
		}

		i = array[k]/2
		steps = 1L
		while(i >= 1){
			cost[i] += BIG+steps
			i /= 2
			steps++
		}

	}

	var answer = Long.MAX_VALUE
	for(k in 1..MAX){
		if(cost[k] >= BIG*n.toLong())
		answer = min(answer,cost[k]-BIG*n.toLong())
	}

	println(answer)
}
