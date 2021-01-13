import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toInt()}

		//get longest sorted suffix
		var i = n
		for(k in n downTo 1){
			if(array[k-1] == k) i--
			else break
		}

		var answer = 1.0
		for(k in 0 until m){
			val query = f.readLine().split(" ")
			val x = query[0].toInt()
			val p = query[1].toDouble()

			if(x >= i){
				answer *= 1.0-p
			}

		}
		if(i == 0) println(1.0)
		else println(1.0-answer)
	}
}
