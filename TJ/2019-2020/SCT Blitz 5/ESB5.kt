import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val adj = Array(n+1){mutableListOf<Int>()}

	for(k in 0 until n-1){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}

	val dp = Array(n+1){IntArray(2){0}}
	//not including is 0, including is 1


	fun dfs(v : Int, p : Int){

		dp[v][0] = 0
		dp[v][1] = 1

		for(nei in adj[v]){
			if(nei == p) continue
			dfs(nei,v)

			dp[v][0] += max(dp[nei][0],dp[nei][1])
			dp[v][1] += dp[nei][0]

		}
	}

	dfs(1,-1)

	val answer = HashSet<Int>()

	//i==0 means can't put v, i==1 means can put v
	fun backtrack(v : Int, p : Int, i : Int){
		if(i==1){
			answer.add(v)
			for(nei in adj[v]){
				if(nei == p) continue
				backtrack(nei,v,0)
			}
		} else {
			for(nei in adj[v]){
				if(nei == p) continue
				if(dp[nei][0] > dp[nei][1]){
					backtrack(nei,v,0)
				} else {
					backtrack(nei,v,1)
				}
			}
		}
	}

	if(dp[1][0] > dp[1][1]){
		backtrack(1,-1,0)
	} else {
		backtrack(1,-1,1)
	}


	println("${answer.size}")
	println(answer.joinToString(" "))

}
