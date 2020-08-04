import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		val seen = HashSet<Int>()

		val answer = mutableListOf<Int>()

		for(k in 0 until 2*n){
			if(seen.contains(array[k])) continue
			seen.add(array[k])
			answer.add(array[k])
		}

		println(answer.joinToString(" "))
	}
}
