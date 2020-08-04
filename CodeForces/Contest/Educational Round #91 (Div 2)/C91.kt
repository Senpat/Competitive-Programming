import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,mi) = f.readLine().split(" ").map{it.toInt()}
		val m = mi.toLong()

		val array = f.readLine().split(" ").map{it.toLong()}.sorted()

		var size = 1L
		var answer = 0

		for(k in n-1 downTo 0){
			if(array[k]*size >= m){
				answer++
				size = 1L
			} else {
				size++
			}
		}

		println(answer)
	}
}
