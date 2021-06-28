import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

		val answer = mutableListOf<Int>()

		for(k in 0 until n){
			answer.add(array[k])
			answer.add(array[k+n])
		}

		println(answer.joinToString(" "))
	}
}
