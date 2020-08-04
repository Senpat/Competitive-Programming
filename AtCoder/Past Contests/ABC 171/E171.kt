import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

	var xor = 0
	for(k in 0 until n){
		xor = xor xor array[k]
	}

	val answer = IntArray(n)
	for(k in 0 until n){
		answer[k] = xor xor array[k]
	}

	println(answer.joinToString(" "))
}
