import java.io.*
import java.util.*
import kotlin.math.min
fun main(){
	for(q in 1..readLine()!!.toInt()){
		val n = readLine()!!.toInt()
		val array = readLine()!!.split(" ").map{it.toInt()}.sorted()

		var answer = Int.MAX_VALUE
		for(k in 1 until n){
			answer = min(answer,array[k]-array[k-1])
		}
		println(answer)
	}
}
