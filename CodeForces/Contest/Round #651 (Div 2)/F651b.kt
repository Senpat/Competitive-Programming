import java.io.*
import java.util.*
import kotlin.math.*
//solves F2, semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val adj = Array(n+1){mutableListOf<Int>()}

		for(k in 2..n){
			val (a,b) = f.readLine().split(" ").map{it.toInt()}
			adj[a].add(b)
			adj[b].add(a)
		}

		//get length of path
		val sjq1 = StringJoiner(" ")
		sjq1.add("? $n")
		for(k in 1..n){
			sjq1.add("$k")
		}

		println(sjq1)
		System.out.flush()

		val (peak,dq1) = f.readLine().split(" ").map{it.toInt()}

		val length = dq1+1

		val depths = IntArray(n+1)
		val parents = IntArray(n+1)
		val depthset = Array(n){hashSetOf<Int>()}
		depths[peak] = 0
		var maxdepth = 0
		fun getdepths(v : Int, p : Int){
			parents[v] = p
			depthset[depths[v]].add(v)
			maxdepth = max(maxdepth,depths[v])

			for(nei in adj[v]){
				if(nei == p) continue
				depths[nei] = depths[v]+1
				getdepths(nei,v)
			}

		}
		getdepths(peak,-1)
		//println(depths.joinToString(" "))
		//find deepest node with same d value as dq1
		var l = (length)/2
		var r = min(length,maxdepth)
		var ans = -1

		while(l <= r){
			val mid = l + (r-l)/2

			//println("$l $r")
			//query
			val sjq = StringJoiner(" ")
			sjq.add("? ${depthset[mid].size}")
			for(i in depthset[mid]){
				sjq.add("$i")
			}
			println(sjq)
			System.out.flush()
			val (xq,dq) = f.readLine().split(" ").map{it.toInt()}

			if(dq == dq1){
				ans = xq
				l = mid+1
			} else {
				r = mid-1
			}
		}

		//ans is one of the nodes

		val nogo = HashSet<Int>()
		var i = ans
		while(i != peak){
			nogo.add(i)
			i = parents[i]
		}
		val otherdepth = length - depths[ans] -1

		//println("$pathpeak $otherdepth")

		val finalqset = HashSet<Int>()

		fun finaldfs(v : Int){
			if(depths[v] == otherdepth) finalqset.add(v)

			for(nei in adj[v]){
				if(nei == parents[v] || nogo.contains(nei)) continue
				finaldfs(nei)

			}
		}
		finaldfs(peak)
		val sjfinal = StringJoiner(" ")
		sjfinal.add("? ${finalqset.size}")

		for(k in finalqset){
			sjfinal.add("$k")
		}

		println(sjfinal)
		System.out.flush()

		val (xqfinal,dqfinal) = f.readLine().split(" ").map{it.toInt()}

		println("! $ans $xqfinal")
		System.out.flush()

		val verdict = f.readLine()


	}
}
