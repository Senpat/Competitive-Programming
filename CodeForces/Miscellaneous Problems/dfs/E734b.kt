import java.io.*
import java.util.*
import kotlin.math.*
//also wrong logic
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = arrayOf(1) + f.readLine().split(" ").map{it.toInt()}

	val adj = Array(n+1){mutableListOf<Int>()}

	for(k in 2..n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}

	val dp = Array(n+1){IntArray(2)}

	fun dfs(v : Int, p : Int){
		if(array[v] == 0){
			var sum0 = 0
			var sum1 = 0
			var num0nei = 0

			for(nei in adj[v]){
				if(nei == p) continue
				dfs(nei,v)
				sum0 += dp[nei][0]
				sum1 += dp[nei][1]
				if(array[nei] == 0) num0nei++
			}

			dp[v][0] = sum0
			dp[v][1] = sum1 + 1 - num0nei

		} else {
			var sum0 = 0
			var sum1 = 0
			var num1nei = 0

			for(nei in adj[v]){
				if(nei == p) continue
				dfs(nei,v)
				sum0 += dp[nei][0]
				sum1 += dp[nei][1]
				if(array[nei] == 1) num1nei++
			}

			dp[v][0] = sum0 + 1 - num1nei
			dp[v][1] = sum1

		}
	}

	dfs(1,-1)

	for(k in 0..1){
		for(j in 1..n){
			print("${dp[j][k]} ")
		}
		println()
	}

	val answer = min(dp[1][0],dp[1][1])

	println(answer)

}
