import java.io.*
import java.util.*
import kotlin.math.*
//wrong logic
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

	//turn white to black
	var wtb = 0
	val seenwtb = HashSet<Int>()

	fun dfswtb(v : Int){
		for(nei in adj[v]){
			if(!seenwtb.contains(nei) && array[nei] == 0){
				seenwtb.add(nei)
				dfswtb(nei)
			}
		}
	}

	for(k in 1..n){
		if(!seenwtb.contains(k) && array[k] == 0){
			wtb++
			dfswtb(k)
		}
	}

	//black to whtie
	var btw = 0
	val seenbtw = HashSet<Int>()

	fun dfsbtw(v : Int){
		for(nei in adj[v]){
			if(!seenbtw.contains(nei) && array[nei] == 1){
				seenbtw.add(nei)
				dfsbtw(nei)
			}
		}
	}

	for(k in 1..n){
		if(!seenbtw.contains(k) && array[k] == 1){
			btw++
			dfsbtw(k)
		}
	}

	val answer = min(wtb,btw)
	println(answer)
}
