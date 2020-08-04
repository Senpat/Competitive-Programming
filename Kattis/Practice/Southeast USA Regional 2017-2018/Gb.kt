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

	val deleted = HashSet<Int>()


	var fail = false

	fun dfs(v : Int, p : Int, c : Int){
		if(deleted.contains(v)) return
		deleted.add(v)

		for(e in adj[v]){
			if(e.first == p) continue
			if(e.second == c){
				fail = true
			}
			dfs(e.first,v,e.second)
		}
	}

	for(k in 1..n){
		if(deleted.contains(k)) continue
		val freq = HashMap<Int,Int>()
		val remove = HashSet<Int>()

		for(e in adj[k]){
			freq[e.second] = (freq[e.second] ?: 0) + 1
			if(freq[e.second]!! >= 2) remove.add(e.second)
		}

		for(e in adj[k]){
			if(remove.contains(e.second)){
				dfs(e.first,k,e.second)
			}
		}
	}

	if(fail){
		println(0)
	} else {
		//print everything not in deleted
		println("${n-deleted.size}")
		for(k in 1..n){
			if(!deleted.contains(k)){
				println(k)
			}
		}
	}


}
