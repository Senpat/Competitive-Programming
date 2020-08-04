import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val array = Array<Pair<Int,Int>>(m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		Pair(a,b)
	}

	var l = 0
	var r = m-1
	var ans = -1



	while( l <= r){
		val mid = l + (r-l)/2

		val out1 = Array(n+1){mutableListOf<Int>()}
		val in1 = Array(n+1){mutableListOf<Int>()}
		for(k in 0..mid){
			out1[array[k].first].add(array[k].second)
			in1[array[k].second].add(array[k].first)
		}








		fun check() : Boolean{
			//find num end and num start
			var numstart = 0
			var start = -1
			var numend = 0
			for(k in 1..n){
				if(out1[k].size == 0 && in1[k].size == 0) return false
				if(out1[k].size > 0 && in1[k].size == 0){
					numstart++
					start = k
				}
				if(out1[k].size == 0 && in1[k].size > 0) numend++
			}

			if(!(numstart == 1 && numend == 1)) return false

			fun getlongestpath() : Int{
				val dp = IntArray(n+1)

				// Visited array to know if the node
				// has been visited previously or not
				val visited = BooleanArray(n+1)

				// Call DFS for every unvisited vertex

				fun dfs(node : Int){
				  // Mark as visited
				  visited[node] = true;

				  // Traverse for all its children
				  for (i in 0 until out1[node].size)
				  {

						// If not visited
						if (!visited[out1[node][i]])
							 dfs(out1[node][i]);

						// Store the max of the paths
						dp[node] = max(dp[node], 1 + dp[out1[node][i]]);
				  }
				 }


				dfs(start)
				var ret = 0;

				// Traverse and find the maximum of all dp[i]
				for (i in 1..n)
				{
					 ret = max(ret, dp[i]);
				}
				//println("$mid $start $ret " + dp.joinToString(" "))
				return ret;
		  }


			return getlongestpath()==n-1
		}


		if(check()){
			ans = mid
			r = mid-1
		} else {
			l = mid+1
		}
	}

	if(ans >= 0) ans++
	println(ans)
}
