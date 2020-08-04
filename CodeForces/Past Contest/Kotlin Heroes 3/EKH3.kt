import java.io.*
import java.util.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val adj = Array(n+1){HashSet<Int>()}
		for(k in 0 until n-1){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			adj[a].add(b)
			adj[b].add(a)
		}

		val answer = Array(n+1){true}

		//count leaves
		var leaves = 0
		for(k in 1..n){
			if(adj[k].size == 1) leaves++
		}

		if(leaves < m){
			println("No")
			continue
		} else if(m == 1){
			println("Yes\n1\n1")
			continue
		}

		for(k in 1..n){
			if(leaves == m) break
			if(!answer[k]) continue
			if(adj[k].size == 1){
				//get rid of this leaf

				var v = k
				while(adj[v].size == 1){
					answer[v] = false
					for(nei in adj[v]){
						adj[nei].remove(v)
						v = nei
					}


				}
				leaves--
			}

		}

		val sb = StringBuilder()
		var count = 0
		for(k in 1..n){
			if(answer[k]){
				count++
				sb.append("$k ")
			}
		}
		println("Yes\n$count")
		println(sb)


	}
}
