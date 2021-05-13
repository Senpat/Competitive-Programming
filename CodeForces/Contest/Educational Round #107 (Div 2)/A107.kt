import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}
		var answer = 0
		for(k in 0 until n){
			if(array[k] == 1 || array[k] == 3) answer++
		}

		println(answer)



	}
}
