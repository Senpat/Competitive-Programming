import java.io.*
import java.util.*
import kotlin.math.*

fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val bigs = 70746471270782959L
	val mids = 4391633L
	val smalls = 22440312119665440L

	val sb = StringBuilder()
	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val adj = Array(n+1){mutableListOf<Int>()}
		for(k in 2..n){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			adj[a].add(b)
			adj[b].add(a)
		}

		val answer = LongArray(n+1){0L}

		//get center of tree

		//get longest point from node 1
		val q1 : Queue<Int> = LinkedList<Int>()
		val seen1 = BooleanArray(n+1){false}
		q1.add(1)
		seen1[1] = true
		var leaf1 = 1

		while(!q1.isEmpty()){
			val v = q1.poll()
			leaf1 = v
			for(nei in adj[v]){
				if(seen1[nei]) continue
				seen1[nei] = true
				q1.add(nei)
			}
		}

		val q2 : Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()
		val seen2 = BooleanArray(n+1){false}
		q2.add(Pair(leaf1,0))
		seen2[leaf1] = true
		var leaf2 = leaf1

		var dist1 = IntArray(n+1){0}
		while(!q2.isEmpty()){
			val p = q2.poll()
			val v = p.first
			val d = p.second
			dist1[v] = d
			leaf2 = v
			for(nei in adj[v]){
				if(seen2[nei]) continue
				seen2[nei] = true
				q2.add(Pair(nei,d+1))
			}
		}

		val q3 : Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()
		val seen3 = BooleanArray(n+1){false}
		q3.add(Pair(leaf2,0))
		seen3[leaf2] = true

		var dist2 = IntArray(n+1){0}
		while(!q3.isEmpty()){
			val p = q3.poll()
			val v = p.first
			val d = p.second
			dist2[v] = d
			for(nei in adj[v]){
				if(seen3[nei]) continue
				seen3[nei] = true
				q3.add(Pair(nei,d+1))
			}
		}

		var mindif = n+1
		var center = -1

		for(k in 1..n){
			if(abs(dist1[k]-dist2[k]) < mindif){
				mindif = abs(dist1[k]-dist2[k])
				center = k
			}
		}

		answer[center] = bigs

		//get depth of 19
		fun dfs(v : Int, p : Int, d : Int){
			if(d == 19) answer[v] = mids
			else{
				for(nei in adj[v]){
					if(nei == p) continue
					dfs(nei,v,d+1)
				}
			}
		}

		dfs(center,-1,1)

		for(k in 1..n){
			if(answer[k] == 0L) answer[k] = smalls
		}

		sb.appendln(answer.joinToString(" ").substring(3))

	}
	println(sb)
}
