import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	var prev = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray()
	//val n = 200000
	//val m = 200000
	//val prev = IntArray(n+1)

	var cur = IntArray(n+1){1}
	val pqr = PriorityQueue<Int>()
	val pql = PriorityQueue<Int>(compareBy{it*-1})
	var i = 1
	while(i <= m){


		for(k in 1..n){
			while(!pqr.isEmpty() && pqr.peek() < k) pqr.poll()
			cur[k] += pqr.size
			pqr.add(k+prev[k])
		}


		for(k in n downTo 1){
			while(!pql.isEmpty() && pql.peek() > k) pql.poll()
			cur[k] += pql.size
			pql.add(k-prev[k])
		}

		var done = true
		for(k in 1..n){
			prev[k] = cur[k]
			cur[k] = 1
			if(prev[k] < n) done = false
		}

		if(done) break
		pqr.clear()
		pql.clear()
		i++
	}


	println(prev.joinToString(" ").substring(2))
 	//println(i)
}
