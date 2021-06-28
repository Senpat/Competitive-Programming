import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = LongArray(1){0L} + f.readLine().split(" ").map{it.toLong()}.toLongArray() + LongArray(1){0L}

		var subs : Long = 0L
		for(k in 1..n){
			val diff = max(0L,array[k]-max(array[k-1],array[k+1]))
			subs += diff

			array[k] -= diff
		}

		var height : Long = 0L
		for(k in 1..n){
			height += max(0L,array[k]-array[k-1])
			height += max(0L,array[k]-array[k+1])
		}

		println(subs+height)
	}
}
