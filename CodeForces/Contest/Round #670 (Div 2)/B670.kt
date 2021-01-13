import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}.sorted()

		var answer = 0L

		//top 5
		var top5 = 1L

		for(k in 0 until 5){
			top5 *= array[n-k-1]
		}

		answer = top5

		answer = max(answer,array[0]*array[1]*array[n-3]*array[n-2]*array[n-1])
		answer = max(answer,array[0]*array[1]*array[2]*array[3]*array[n-1])

		println(answer)
	}
}
