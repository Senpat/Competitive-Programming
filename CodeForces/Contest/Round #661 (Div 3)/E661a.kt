import java.io.*
import java.util.*
import kotlin.math.*
//solves E1
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (nl,s) = f.readLine().split(" ").map{it.toLong()}
		val n = nl.toInt()

		val adj = Array(n+1){mutableListOf<Edge>()}
		val edges = Array<Edge>(n-1){Edge(0,0,0L,0)}
		for(k in 0 until n-1){
			val (a,b,c) = f.readLine().split(" ").map{it.toInt()}

			adj[a].add(Edge(a,b,c.toLong(),k))
			adj[b].add(Edge(b,a,c.toLong(),k))

			edges[k] = Edge(a,b,c.toLong(),k)
		}

		//dfs to get parents and leaf sums
		val parents = IntArray(n+1)
		val leafs = IntArray(n+1)

		fun dfs(v : Int, p : Int){
			parents[v] = p

			if(v != 1 && adj[v].size == 1) leafs[v] = 1
			else leafs[v] = 0

			for(nei in adj[v]){
				if(nei.b == p) continue
				dfs(nei.b,v)
				leafs[v] += leafs[nei.b]
			}
		}

		dfs(1,-1)

		//println(leafs.joinToString(" "))
		//println(parents.joinToString(" "))

		var cursum = 0L

		val weights = LongArray(n-1){0}
		for(k in 0 until n-1){
			if(parents[edges[k].a]==edges[k].b){
				weights[k] = leafs[edges[k].a].toLong()
			} else {
				weights[k] = leafs[edges[k].b].toLong()
			}
		}

		//println(weights.joinToString(" "))

		val compare = LongArray(n-1){
			(edges[it].w/2-edges[it].w) * weights[it]
		}

		//greatest edge index to smallest
		val pq = PriorityQueue<Int>(compareBy{compare[it]})
		for(k in 0 until n-1){
			pq.add(k)
			cursum += edges[k].w * weights[k]
		}


		var answer = 0
		while(cursum > s){
			answer++
			//pop biggest
			val i = pq.poll()

			cursum -= edges[i].w*weights[i]
			edges[i].w /= 2L
			cursum += edges[i].w*weights[i]

			compare[i] = (edges[i].w/2-edges[i].w) * weights[i]
			if(edges[i].w > 0) pq.add(i)
		}

		println(answer)
	}
}
data class Edge(val a : Int, val b : Int, var w : Long, val i : Int)
