import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()
		val friends = f.readLine().split(" ").map{it.toInt()}.toIntArray().sortedDescending()

		var answer : Long= 0L

		for(k in n-m until n){
			answer += array[k]
			if(friends[k-(n-m)] == 1) answer += array[k]
		}

		
		var i = 0
		for(k in 0 until m){
			if(friends[k] == 1) break
			answer += array[i]
			i += friends[k]-1

		}

		println(answer)


	}
}
