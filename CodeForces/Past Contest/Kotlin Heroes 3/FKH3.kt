import java.io.*
import java.util.*
import kotlin.math.*
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val array = Array(n){j ->
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			Triple(a,b,j)
		}

		val pq = PriorityQueue<Triple<Int,Int,Int>>(compareBy({it.second}))
		val start = PriorityQueue<Triple<Int,Int,Int>>(compareBy({it.first},{it.second}))

		for(k in 0 until n){
			start.add(array[k])
		}

		var d = 0
		val answer = IntArray(n)

		var t = 0
		var added = 0

		while(added < n){
			if(pq.isEmpty()){
				t = start.peek().first
			}

			while(!start.isEmpty() && start.peek().first <= t){
				pq.add(start.poll())
			}

			for(k in 1..m){
				if(pq.isEmpty()) break
				val triple = pq.poll()
				answer[triple.third] = t
				d = max(d,t-triple.second)
				added++
			}

			t++

		}
		println(d)
		println(answer.joinToString(" "))


	}
}
