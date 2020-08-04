import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val adj = Array(n+1){HashSet<Pair<Int,Int>>()}
	//first is other node, second is color
	for(k in 2..n){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(Pair(b,c))
		adj[b].add(Pair(a,c))
	}

	val badedges = HashSet<Triple<Int,Int,Int>>()

	val centers = HashSet<Int>()

	for(k in 1..n){
		val freq = HashMap<Int,Int>()
		val remove = HashSet<Int>()

		for(e in adj[k]){
			freq[e.second] = (freq[e.second] ?: 0) + 1
			if(freq[e.second]!! >= 2) remove.add(e.second)
		}

		if(remove.size > 0){
			centers.add(k)
		}

		for(e in adj[k]){
			if(remove.contains(e.second)){
				badedges.add(Triple(k,e.first,e.second))
				badedges.add(Triple(e.first,k,e.second))
			}
		}
	}

	for(trip in badedges){
		if(adj[trip.first].contains(Pair(trip.second,trip.third))) adj[trip.first].remove(Pair(trip.second,trip.third))
	}

	if(centers.size == 0){
		//all work
		println("$n")
		for(k in 1..n){
			println("$k")
		}
	} else {
		//get random center
		var center = -1
		for(i in centers){
			center = i
			break
		}

		val seen = HashSet<Int>()

		fun dfs(v : Int){

			for(nei in adj[v]){
				if(seen.contains(nei.first)) continue
				seen.add(nei.first)
				dfs(nei.first)
			}
		}

		seen.add(center)
		dfs(center)

		//checks that all of a is in b
		fun check(a : HashSet<Int>, b : HashSet<Int>) : Boolean{
			for(i in a){
				if(!b.contains(i)) return false
			}
			return true
		}

		if(check(centers,seen)){
			val pq = PriorityQueue<Int>()
			for(i in seen) pq.add(i)

			println("${seen.size}")
			while(!pq.isEmpty()) println("${pq.poll()}")
		} else {
			println("0")
		}
	}


}
