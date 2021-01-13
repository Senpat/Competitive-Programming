import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val circles = Array<Triple<Int,Int,Int>>(n){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
		Triple(a,b,c)
	}

	val adj = Array(n+1){mutableListOf<Int>()}

	val left = HashSet<Int>()
	val right = HashSet<Int>()

	for(k in 0 until n){
		if(circles[k].third >= circles[k].first) left.add(k)
		if(circles[k].third >= 200-circles[k].first) right.add(k)

		for(j in k+1 until n){
			if((circles[k].third + circles[j].third)*(circles[k].third + circles[j].third) >= ((circles[k].first-circles[j].first)*(circles[k].first-circles[j].first) + (circles[k].second-circles[j].second)*(circles[k].second-circles[j].second))){
				adj[k].add(j)
				adj[j].add(k)
			}
		}
	}

	var min = n+1
	for(start in left){
		//bfs from start

		val q : Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()
		//first is node, second is max

		val seen = HashSet<Int>()

		val value = IntArray(n+1){n+4}

		q.add(Pair(start,start))
		seen.add(start)

		while(!q.isEmpty()){
			val p = q.poll()
			if(right.contains(p.first)){
				min = min(min,p.second)
				break
			}
			//println("${p.first}:" + adj[p.first].joinToString())
			for(nei in adj[p.first]){
				val newvalue = max(nei,p.second)
				if(newvalue < value[nei]){
					value[nei] = newvalue
					q.add(Pair(nei,newvalue))
				}
			}
		}

		//println("$start " + value.joinToString(" "))

	}

	println(min)

}
