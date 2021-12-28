import java.io.*
import java.util.*
import kotlin.math.*

fun main(args : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		val hasval = Array(n+1){mutableListOf<Int>()}
		for(k in 0 until n){
			hasval[array[k]].add(k)
		}

		//stores indeces of array
		val pq = PriorityQueue<Int>(compareBy({-array[it]},{it}))

		val answer = IntArray(n){0}

		for(k in 1..(2*n)){
			if(k <= n){
				for(i in hasval[k]){
					pq.add(i)
				}
			}

			if(!pq.isEmpty()){
				val i = pq.poll()
				answer[i] = k
			}
		}

		println(answer.joinToString(" "))

	}
}
