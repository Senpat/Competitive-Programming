import java.io.*
import java.util.*
import kotlin.math.*

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
	val parent = IntArray(n+1)

	fun dfs(v : Int, p : Int){
		parent[v] = p
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

	val excess01 = LongArray(n+1)
	val excess10 = LongArray(n+1)

	val seen = HashSet<Int>()

	val pq = PriorityQueue<Int>(compareBy({values[it]}))
	for(k in 1..n) pq.add(k)

	fun dfs2(v : Int, p : Int){
		if(start[v] == 0 && end[v] == 1) excess01[v]++
		if(start[v] == 1 && end[v] == 0) excess10[v]++
		seen.add(v)
		for(nei in adj[v]){
			if(nei == p) continue
			if(!seen.contains(nei)){
				dfs2(nei,v)
			}
			excess01[v] += excess01[nei]
			excess10[v] += excess10[nei]
		}

	}

	var answer = 0L
	while(!pq.isEmpty()){
		val v = pq.poll()
		if(seen.contains(v)) continue

		dfs2(v,parent[v])

		//println("$v ${excess01[v]} ${excess10[v]}")



		val min = min(excess01[v],excess10[v])
		excess01[v]-=min
		excess10[v]-=min

		answer += min*2L*values[v]

	}

	println(answer)

}
