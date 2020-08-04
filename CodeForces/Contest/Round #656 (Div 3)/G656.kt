import java.io.*
import java.util.*
import kotlin.math.*
//in contest, fail (starts at non)
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val a = f.readLine().split(" ").map{it.toInt()}
		val b = f.readLine().split(" ").map{it.toInt()}


		val swaps = HashSet<Int>()
		fun check() : Boolean{
			val freq = IntArray(n+1){0}

			val freqa = IntArray(n+1){0}
			for(k in 0 until n){
				freq[a[k]]++
				freq[b[k]]++

				//fill swaps while you're at it
				freqa[a[k]]++
			}

			for(k in 1..n){
				if(freqa[k] != 1) swaps.add(k)
			}

			for(k in 1..n){
				if(freq[k] != 2) return false
			}
			return true
		}


		if(!check()){
			println(-1)
			continue
		}

		val adj = Array(n+1){mutableListOf<Edge>()}
		for(k in 0 until n){
			if(a[k] == b[k]) continue
			adj[a[k]].add(Edge(b[k],k+1))
			adj[b[k]].add(Edge(a[k],k+1))
		}

		val answer = mutableListOf<Int>()

		val seen = HashSet<Int>()
		val curcomponent = HashSet<Int>()
		var noswap = -1

		fun getcomp(v : Int){

			if(noswap == -1 && !swaps.contains(v)) noswap = v
			curcomponent.add(v)
			for(nei in adj[v]){
				if(seen.contains(nei.to)) continue
				seen.add(nei.to)
				getcomp(nei.to)
			}
		}


		//for every component
		for(k in 1..n){
			if(seen.contains(k) || adj[k].size != 2) continue
			//get component and find nonstarred
			seen.add(k)
			curcomponent.clear()
			if(!swaps.contains(k)) noswap = k

			getcomp(k)

			if(curcomponent.size == 2){
				if(swaps.contains(k)){
					for(nei in adj[k]){
						answer.add(nei.i)
						break
					}
				}
				continue
			}

			var start = noswap
			if(start == -1) start = k

			//try both directions
			var path1 = mutableListOf<Int>()
			var path2 = mutableListOf<Int>()

			var active1 = swaps.contains(start)
			var active2 = swaps.contains(start)

			var nei1 = -1
			var nei2 = -1
			for(nei in adj[start]){
				if(nei1 == -1){
					nei1 = nei.to
					if(active1){
						path1.add(nei.i)
						if(swaps.contains(nei1)) active1 = false
					} else {
						if(swaps.contains(nei1)) active1 = true
					}
				} else {
					nei2 = nei.to
					if(active2){
						path2.add(nei.i)
						if(swaps.contains(nei2)) active2 = false
					} else {
						if(swaps.contains(nei2)) active2 = true
					}
				}
			}
			//println("$start $nei1 $nei2")
			var prev1 = start
			var v1 = nei1
			while(v1 != start){
				//get the neighbor and edge index of next node
				var next = -1
				var nextindex = -1

				for(neis in adj[v1]){
					if(neis.to == prev1) continue
					next = neis.to
					nextindex = neis.i
					break
				}

				if(active1){
					path1.add(nextindex)
					if(swaps.contains(next)) active1 = false
				} else {
					if(swaps.contains(next)) active1 = true
				}

				prev1 = v1
				v1 = next
			}

			var prev2 = start
			var v2 = nei2
			while(v2 != start){
				//get the neighbor and edge index of next node
				var next = -1
				var nextindex = -1

				for(neis in adj[v2]){
					if(neis.to == prev2) continue
					next = neis.to
					nextindex = neis.i
					break
				}

				if(active2){
					path2.add(nextindex)
					if(swaps.contains(next)) active2 = false
				} else {
					if(swaps.contains(next)) active2 = true
				}

				prev2 = v2
				v2 = next
			}

			if(path1.size < path2.size){
				for(i in path1) answer.add(i)
			} else {
				for(i in path2) answer.add(i)
			}


		}


		println(answer.size)
		println(answer.joinToString(" "))
	}
}

data class Edge(val to : Int, val i : Int)
