import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val adj = Array(n+1){HashSet<Int>()}

		for(k in 2..n){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}

			adj[a].add(b)
			adj[b].add(a)
		}

		if(adj[m].size == 1 || adj[m].size == 0){
			println("Ayush")
		} else if((n-3)% 2 == 1){
			println("Ayush")
		} else {
			println("Ashish")
		}

	}
}
