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

		if(n%2 == 1){
			println("1 ${adj[1][0]}")
			println("1 ${adj[1][0]}")
			continue
		}

		val subsums = IntArray(n+1){0}
		val parents = IntArray(n+1){0}
		fun dfs(v : Int,p : Int){
			subsums[v] = 1
			parents[v] = p
			for(nei in adj[v]){
				if(nei == p) continue
				dfs(nei,v)
				subsums[v] += subsums[nei]
			}
		}

		dfs(1,-1)

		//get centroids
		var c1 = -1
		var c2 = -1



		for(k in 2..n){
			if(subsums[k] == n/2){
				c1 = k
				c2 = parents[k]
				continue
			}

		}

		if(c2 == -1){
			//already unique centroid
			println("1 ${adj[1][0]}")
			println("1 ${adj[1][0]}")
		} else {

			//dfs from c1 and find a leaf
			var leaf = -1
			fun dfs2(v : Int, p : Int){
				if(adj[v].size == 1){
					leaf = v
					return
				}

				for(nei in adj[v]){
					if(nei == p) continue
					dfs2(nei,v)
				}
			}

			dfs2(c1,parents[c1])

			println("$leaf ${adj[leaf][0]}")
			println("$leaf $c2")
		}


	}
}
