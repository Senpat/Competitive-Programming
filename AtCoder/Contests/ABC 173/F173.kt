import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val adj = Array(n+1){mutableListOf<Int>()}
	for(k in 2..n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		adj[a].add(b)
		adj[b].add(a)
	}

	for(k in 1..n) adj[k].sort()

	val a = LongArray(n+1){0L}
	//calc a[1]

	a[1] = 1L
	var prev = 1L
	for(k in 2..n){
		var i = 0L
		for(nei in adj[k]){
			if(nei < k) i++
			else break
		}

		prev = prev-(i-1)
		a[1] += prev
	}

	for(k in 1 until n){
		val neis = mutableListOf<Int>()

		for(nei in adj[k]){
			if(nei > k){
				//("nei $k $nei")
				neis.add(nei)
			}
		}

		if(neis.size == 0){
			a[k+1] = a[k] - (n-k+1)
		} else {
			//println("neis[0] = ${neis[0]}")
			a[k+1] = a[k] - (neis[0]-k)
			for(j in 1 until neis.size){
				a[k+1] = a[k+1] + (n-neis[j]+1).toLong()
			}
		}
	}

	var answer = 0L
	for(k in 1..n){
		//println(a[k])
		answer += a[k]
	}
	println(answer)
}
