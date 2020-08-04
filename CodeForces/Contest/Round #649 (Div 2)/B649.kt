import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}
		val answer = mutableListOf<Int>()
		answer.add(array[0])
		for(k in 1 until n-1){
			if((array[k] > array[k-1]) == (array[k] > array[k+1])) answer.add(array[k])
		}
		answer.add(array[n-1])

		println(answer.size)
		println(answer.joinToString(" "))
	}
}
