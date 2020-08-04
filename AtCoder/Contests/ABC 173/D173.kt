import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray().sortedDescending()

	var answer = array[0]

	var i = 1
	while(i < n-1){
		answer += array[(i+1)/2]
		i++
	}

	println(answer)
}
