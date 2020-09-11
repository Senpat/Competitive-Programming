import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		if(array[0] + array[1] <= array[n-1]){
			println("1 2 $n")
		} else if(array[n-1] - array[n-2] >= array[0]){
			println("1 ${n-1} $n")
		} else {
			println(-1)
		}
	}
}
