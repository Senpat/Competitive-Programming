import java.io.*
import java.util.*
import kotlin.math.*

fun main(天神偶么卡儿 : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toLong()}
		var answer = 0L

		for(k in 1 until n){
			answer += abs(array[k]-array[k-1])-1
		}

		println(answer)
	}

}
