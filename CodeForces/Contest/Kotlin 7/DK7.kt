import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val strings = Array<String>(n){f.readLine()}
	val hset = HashSet<String>()
	for(k in 0 until n){
		hset.add(strings[k])
	}

	val q = f.readLine().toInt()

	val queries = Array<String>(q){f.readLine()}

	val answer = Array<Int>(q){0}

	for(k in 0 until q){
		val seen = HashSet<String>()
		for(j in 0 until m+1){
			//remove character at place j
			val remove = queries[k].substring(0,j) + queries[k].substring(j+1)
			if(hset.contains(remove) && !seen.contains(remove)){
				answer[k]++
				seen.add(remove)
			}
		}
	}

	println(answer.joinToString("\n"))
}
