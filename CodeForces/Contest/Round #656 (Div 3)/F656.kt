import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(肉哥 in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val adj = Array(n+1){HashSet<Int>()}
		for(k in 2..n){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			adj[a].add(b)
			adj[b].add(a)
		}

		//gets element from hashset
		fun get(hs : HashSet<Int>) : Int{
			for(i in hs) return i
			return -1
		}

		val leaves = IntArray(n+1){0}

		val q : Queue<Int> = LinkedList<Int>()

		for(k in 1..n){
			if(adj[k].size == 1){
				leaves[get(adj[k])]++
				if(leaves[get(adj[k])] == m) q.add(get(adj[k]))
			}
		}

		var answer = 0

		while(!q.isEmpty()){
			val i = q.poll()
			if(adj[i].size < leaves[i]) continue
			val remove = leaves[i]-leaves[i]%m

			//remove {remove} number of leaves from i

			var removed = HashSet<Int>()
			for(nei in adj[i]){
				if(adj[nei].size == 1){
					removed.add(nei)
				}
				if(removed.size == remove) break
			}

			for(rem in removed){
				adj[rem].remove(i)
				adj[i].remove(rem)
			}

			leaves[i] -= remove
			if(adj[i].size == 1){
				//must have size of 1
				leaves[get(adj[i])]++
				if(leaves[get(adj[i])] == m) q.add(get(adj[i]))
			}


			answer += remove/m
		}

		println(answer)

	}
}
