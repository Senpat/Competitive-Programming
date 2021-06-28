import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	println("? 1")
	System.out.flush()
	val dists = IntArray(1){0} + f.readLine().split(" ").map{it.toInt()}.toIntArray()

	val adj = Array<IntArray>(n+1){IntArray(n+1){0}}

	var odd = 0
	var even = 0
	for(k in 1..n){
		if(dists[k] == 1){
			adj[1][k] = 1
			adj[k][1] = 1
		}

		if(dists[k] % 2 == 0) even++
		else odd++

	}

	var mod = 0
	if(odd < even) mod = 1

	for(k in 2..n){
		if(dists[k]%2 == mod){
			println("? $k")
			System.out.flush()
			val curdists = IntArray(1){0} + f.readLine().split(" ").map{it.toInt()}.toIntArray()
			for(j in 1..n){
				if(curdists[j] == 1){
					adj[k][j] = 1
					adj[j][k] = 1
				}
			}
		}
	}

	println("!")
	for(k in 1..n){
		for (j in 1 until k){
			if(adj[k][j] == 1){
				println("$k $j")
			}
		}
	}



}
