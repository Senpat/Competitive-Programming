import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

		var max = 0L
		for(k in 0 until n){
			max = max(max,array[k]*(k+1).toLong())
		}

		println(max)
	}
}
