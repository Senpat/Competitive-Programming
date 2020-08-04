import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val adj = Array(n+1){mutableListOf<Int>()}

	for(k in 1..m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}

	val order = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray()

	val q : Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()

	val answer = mutableListOf<Int>()
	val seen = HashSet<Int>()
	val on = IntArray(n+1)					//the number that that vertex should be on
	for(k in 1..n){
		if(order[k] == 1){
			q.add(Pair(k,1))
			seen.add(k)
			on[k] = 1
		}
	}

	while(!q.isEmpty()){
		val p = q.poll()
		val v = p.first
		val i = p.second

		answer.add(v)

		for(nei in adj[v]){
			if(order[nei] == i){
				println(-1)
				return
			}
		}

		for(nei in adj[v]){
			if(!seen.contains(nei) && order[nei] == i+1){
				q.add(Pair(nei,order[nei]))
				seen.add(v)
			}
			on[nei] = max(on[nei],i+1)
		}
	}
	if(answer.size != n) println(-1)
	else println(answer.joinToString(" "))
}
