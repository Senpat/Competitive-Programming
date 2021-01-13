import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val adj = Array(n+1){mutableListOf<Int>()}

		for(k in 0 until n-1){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			adj[a].add(b)
			adj[b].add(a)
		}

		val parents = Array(n+1){-1}
		fun dfs(v : Int, p : Int){
			parents[v] = p
			for(nei in adj[v]){
				if(nei == p) continue
				dfs(nei,v)
			}
		}

		dfs(1,-1)

		val shortest = Array(n+1){Int.MAX_VALUE}

		var answer = 0

		for(k in 1..n){
			if(adj[k].size == 2 || k == 1) continue

			var count = 1
			var v = parents[k]
			while(adj[v].size ==	 2 && v != 1){
				v = parents[v]
				count++
			}

			answer = max(answer,count)
			shortest[v] = min(shortest[v],count)
		}

		if(adj[1].size > 1) answer = max(answer,shortest[1]+1)
		for(k in 2..n){
			if(shortest[k] != Int.MAX_VALUE) answer = max(answer,shortest[k]+1)
		}

		println(answer)
	}


}
