import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val adj = Array(n+1){mutableListOf<Int>()}

	val edges = f.readLine().split(" ").map{it.toInt()}
	for(k in 2..n){
		adj[edges[k-2]].add(k)
	}

	val num = LongArray(1) + f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val leafs = LongArray(n+1){0}
	val subsum = LongArray(n+1){0}

	fun dfs1(v : Int){
		if(adj[v].size == 0) leafs[v] = 1
		subsum[v] = num[v]

		for(nei in adj[v]){
			dfs1(nei)
			leafs[v]+=leafs[nei]
			subsum[v] += subsum[nei]
		}
	}

	dfs1(1)

	var answer = 0L
	for(k in 1..n){
		answer = max(answer,(subsum[k]+leafs[k]-1)/leafs[k])
	}
	println(answer)
}
