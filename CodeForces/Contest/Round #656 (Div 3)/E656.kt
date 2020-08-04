import java.io.*
import java.util.*
import kotlin.math.*
//upsolve
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val adj = Array(n+1){mutableListOf<Int>()}
		val dir = mutableListOf<Edge>()
		val undir = mutableListOf<Edge>()

		for(k in 0 until m){
			val (x,a,b) = f.readLine().split(" ").map{it.toInt()}
			if(x == 0){
				//undirected
				undir.add(Edge(a,b))
			} else {
				//directed
				dir.add(Edge(a,b))
				adj[a].add(b)

			}
		}

		//topological sort
		//if you reach a node that you've visited and is not in the stack, then impossible

		val stk = Stack<Int>()
		val visited = BooleanArray(n+1){false}
		val added = BooleanArray(n+1){false}
		var fail = false

		fun dfs(v : Int){
			for(nei in adj[v]){
				if(visited[nei] && !added[nei]){
					fail = true
					return
				}
				if(visited[nei]) continue
				visited[nei] = true
				dfs(nei)

			}
			stk.add(v)
			added[v] = true
		}


		for(k in 1..n){
			if(visited[k]) continue
			visited[k] = true
			dfs(k)
		}

		if(fail){
			println("NO")
		} else {
			//add to dir
			val top = mutableListOf<Int>()
			while(!stk.isEmpty()){
				top.add(stk.pop())
			}

			val indexof = IntArray(n+1){0}
			for(k in 0 until n){
				indexof[top[k]] = k
			}

			for(e in undir){
				if(indexof[e.a] < indexof[e.b]){
					//put it in normally
					dir.add(Edge(e.a,e.b))
				} else {
					dir.add(Edge(e.b,e.a))
				}
			}

			println("YES")
			val sb = StringBuilder()
			for(e in dir){
				sb.appendln("${e.a} ${e.b}")
			}
			println(sb)
		}


	}
}

data class Edge(val a : Int, val b : Int)
