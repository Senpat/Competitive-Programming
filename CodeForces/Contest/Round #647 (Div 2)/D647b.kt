import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val adj = Array(n+1){mutableListOf<Int>()}

	for(k in 1..m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}

	val order = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray()

	for(k in 1..n){

		val lower = HashSet<Int>()
		for(nei in adj[k]){
			if(order[nei] < order[k]) lower.add(order[nei])
			if(order[nei] == order[k]){
				println(-1)
				return
			}
		}

		if(order[k] > 1 && lower.size != order[k]-1){
			println(-1)
			return
		}
	}

	val pq = PriorityQueue<Pair<Int,Int>>(compareBy({it.second}))
	for(k in 1..n){
		pq.add(Pair(k,order[k]))
	}

	val sj = StringJoiner(" ")
	while(!pq.isEmpty()){
		val p = pq.poll()
		sj.add("${p.first}")
	}

	println(sj)
}
