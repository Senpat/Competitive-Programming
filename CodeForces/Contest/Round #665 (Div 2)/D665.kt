import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 1000000007L

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val adj = Array(n+1){mutableListOf<Int>()}
		for(k in 0 until n-1){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}

			adj[a].add(b)
			adj[b].add(a)
		}

		val m = f.readLine().toInt()
		val pf = f.readLine().split(" ").map{it.toLong()}.sorted()

		val ms = LongArray(n-1){0L}

		if(m <= n-1){
			for(k in 0 until m){
				ms[k+n-1-m] = pf[k]
			}
			for(k in 0 until n-1-m){
				ms[k] = 1L
			}
		} else {
			for(k in 0 until n-2){
				ms[k] = pf[k]
			}
			var prod = 1L
			for(k in n-2 until m){
				prod = (prod*pf[k] + MOD)%MOD
			}
			ms[n-2] = prod
		}

		val subsum = LongArray(n+1){0L}

		fun dfs(v : Int, p : Int){
			subsum[v] = 1L

			for(nei in adj[v]){
				if(nei == p){
					continue
				}
				dfs(nei,v)
				subsum[v] += subsum[nei]
			}
		}

		dfs(1,-1)

		val edgefreqs = mutableListOf<Long>()

		for(k in 2..n){
			edgefreqs.add(subsum[k]*(subsum[1]-subsum[k]))
		}

		edgefreqs.sort()

		var answer = 0L

		for(k in 0 until n-1){
			answer = (answer + edgefreqs[k]*ms[k] + MOD)%MOD
		}

		println(answer)


	}

}
