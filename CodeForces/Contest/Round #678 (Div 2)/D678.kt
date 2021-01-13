import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val adj = Array(n+1){mutableListOf<Int>()}

	val edges = f.readLine().split(" ").map{it.toInt()}
	for(k in 2..n){
		adj[edges[k-2]].add(k)
	}

	val num = LongArray(1) + f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val leafs = LongArray(n+1){0}
	val subsum = LongArray(n+1){0}

	fun dfs1(v : Int){
		if(adj[v].size == 0) leafs[v] = 1
		subsum[v] = num[v]

		for(nei in adj[v]){
			dfs1(nei)
			leafs[v]+=leafs[nei]
			subsum[v] += subsum[nei]
		}
	}

	dfs1(1)

	//println(leafs.joinToString(" "))
	/*println(subsum.joinToString(" ")) */


	var fail = false
	var dp = LongArray(n+1){0}
	fun dfs(v : Int, x : Long){
		if(fail) return

		dp[v] += num[v]

		if(leafs[v] == 1L){
			if(dp[v] > x) fail = true
			return
		}
		
		//distribute dp[v]
		var y = dp[v]
		for(nei in adj[v]){
			val give = x*leafs[nei] - subsum[nei]
			dp[nei] += min(y,give)
			y -= min(y,give)
			dfs(nei,x)
		}

		if(y > 0) fail = true

	}

	fun check(x : Long) : Boolean{
		fail = false
		dp = LongArray(n+1){0}
		dfs(1,x)
		return !fail
	}

	var l = 0L
	var r = 100000000000000L				//1e14
	var ans = -1L
	while (l <= r){
		val mid = l + (r-l)/2
		if(check(mid)){
			ans = mid
			r = mid-1
		} else {
			l = mid+1
		}
	}

	println(ans)
}
