import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val pow10 = LongArray(12){0L}
	pow10[0] = 1L
	for(k in 1 until 12){
		pow10[k] = pow10[k-1]*10L
	}

	for(q in 1..f.readLine().toInt()){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}

		val a1 = pow10[a-1]
		val a2 = pow10[b-1] + pow10[c-1]

		println("$a1 $a2")
	}
}
