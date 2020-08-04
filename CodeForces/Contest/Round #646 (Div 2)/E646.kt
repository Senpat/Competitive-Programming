import java.io.*
import java.util.*
import kotlin.math.*
//completely wrong
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val values = LongArray(n+1)
	val start = IntArray(n+1)
	val end = IntArray(n+1)

	for(k in 1..n){
		val line = f.readLine().split(" ")
		values[k] = line[0].toLong()
		start[k] = line[1].toInt()
		end[k] = line[2].toInt()
	}

	val adj = Array(n+1){mutableListOf<Int>()}
	for(k in 2..n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}

	val count10 = LongArray(n+1)
	val count01 = LongArray(n+1)

	val MAX = 10000000000000000L

	fun dfs(v : Int, p : Int){
		if(start[v] == 0 && end[v] == 1) count01[v]++
		if(start[v] == 1 && end[v] == 0) count10[v]++
		for(nei in adj[v]){
			if(nei == p) continue
			dfs(nei,v)
			count01[v] += count01[nei]
			count10[v] += count10[nei]
		}
	}

	dfs(1,-1)

	if(count01[1] != count10[1]){
		println(-1)
		return
	}

	fun dfs2(v : Int, p : Int) : Long{
		if(count01[v] == 0L && count10[v] == 0L) return 0L
		//shuffle at v
		var answer1 = (count01[v]+count10[v])*values[v]

		var answer2 = MAX
		if(start[v] == end[v]){
			answer2 = 0L
			for(nei in adj[v]){
				if(nei == p) continue
				answer2 += dfs2(nei,v)
				if(answer2 > answer1){
					return answer1
				}
			}
		}

		return min(answer1,answer2)
	}

	val answer = dfs2(1,-1)
	println(answer)


}
