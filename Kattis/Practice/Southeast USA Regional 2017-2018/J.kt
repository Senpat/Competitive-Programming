import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val gold = IntArray(n+1){0}
	val dec = IntArray(n+1){0}

	for(k in 1..n){
		val (g,d) = f.readLine().split(" ").map{it.toInt()}
		gold[k] = g
		dec[k] = d
	}

	val adj = Array(n+1){mutableListOf<Edge>()}

	for(k in 0 until m){
		val (a,b,t) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(Edge(b,t))
		adj[b].add(Edge(a,t))
	}

	val dp = Array(n+1){IntArray(1010){0}}

	dp[1][1] = gold[1]

	var answer = 0
	for(k in 1..1005){
		for(j in 1..n){
			if(dp[j][k] == 0) continue
			answer = max(dp[j][k],answer)
			for(e in adj[j]){
				if(e.t + k >= 1005) continue
				dp[e.to][e.t+k] = max(dp[e.to][e.t+k],dp[j][k] + max(0,gold[e.to]-(e.t+k-1)*dec[e.to]))
			}
		}
	}



	println(answer)

}

data class Edge(val to: Int, val t: Int)
