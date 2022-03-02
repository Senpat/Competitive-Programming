import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	//edge is to, dir (0 if edge was (from, to), 1 if edge is (to, from))
	val adj = Array<MutableList<Pair<Int,Int>>>(n+1){mutableListOf<Pair<Int,Int>>()}

	val edges = Array<Pair<Int,Int>>(m){Pair(-1,-1)}

	for(k in 0 until m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}

		adj[a].add(Pair(b,0))
		adj[b].add(Pair(a,1))

		edges[k] = Pair(a,b)
	}

	//find 3-coloring: 0,1,2
	val coloring = IntArray(n+1){-1}
	val seen = HashSet<Int>()
	var fail = false
	fun dfs(v : Int){
		val has = BooleanArray(3){false}
		for(p in adj[v]){
			if(coloring[p.first] != -1) has[coloring[p.first]] = true
		}

		var color = -1
		for(k in 0 until 3){
			if(!has[k]){
				color = k
				break
			}
		}

		if(color == -1){
			fail = true
			return
		}

		coloring[v] = color

		for(p in adj[v]){
			if(!seen.contains(p.first)){
				seen.add(p.first)
				dfs(p.first)
			}
		}

	}

	seen.add(1)
	dfs(1)

	if(fail){
		println("NO")
	} else {
		println("YES")
		val answer = IntArray(m){0}
		//0 -> 1, 1 -> 2, 0 -> 2
		for(k in 0 until m){
			val a = edges[k].first
			val b = edges[k].second
			if(coloring[a] == 0 && coloring[b] == 1) answer[k] = 1
			if(coloring[a] == 1 && coloring[b] == 2) answer[k] = 1
			if(coloring[a] == 0 && coloring[b] == 2) answer[k] = 1
		}

		println(answer.joinToString(""))
	}

}
