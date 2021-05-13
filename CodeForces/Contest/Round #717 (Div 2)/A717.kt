import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		for(k in 0 until n-1){
			if(m == 0) break
			if(m <= array[k]){
				array[k] -= m
				array[n-1] += m
				m = 0
			} else {
				array[n-1] += array[k]
				m -= array[k]
				array[k] = 0
			}
		}

		println(array.joinToString(" "))

	}
}
