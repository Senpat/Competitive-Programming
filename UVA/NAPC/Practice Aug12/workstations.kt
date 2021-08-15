import java.io.*
import java.util.*
import kotlin.math.*
//Assigning Workstations
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val intervals = Array<Pair<Int,Int>>(n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		Pair(a,a+b)
	}.sortedWith(compareBy({it.first},{it.second}))

	val pq = PriorityQueue<Int>()

	var count = 0
	for(k in 0 until n){
		val p = intervals[k]
		//println(p)
		while(!pq.isEmpty() && pq.peek() < p.first-m){
			pq.poll()
		}

		if(!pq.isEmpty() && pq.peek() <= p.first){
			pq.poll()
		} else {
			count++
		}
		pq.add(p.second)
	}

	println(n-count)


}
