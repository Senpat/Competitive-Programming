import java.io.*
import java.util.*
import kotlin.math.*

fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,xi) = f.readLine().split(" ").map{it.toInt()}

		val x = xi.toLong()

		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()

		var i = 0

		var ind = 0
		while(ind < n){
			if(x*(1L shl i) >= array[ind]) ind++
			i++
		}

		println(i)
	}
}
