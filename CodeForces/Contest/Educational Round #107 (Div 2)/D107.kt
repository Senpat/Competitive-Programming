import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val best = mutableListOf<Int>()
	best.add(m-1)
	for(k in 0 until m){
		best.add(k)
		for(j in (k+1) until m){
			best.add(k)
			best.add(j)
		}
	}

	val answer = mutableListOf<Char>()
	var i = 0
	for(k in 0 until n){
		answer.add((best[i]+97).toChar())
		i++
		if((k == best.size-1) || (k > best.size-1 && i == best.size-1)){
			i = 0
		}
	}

	println(answer.joinToString(""))

}
