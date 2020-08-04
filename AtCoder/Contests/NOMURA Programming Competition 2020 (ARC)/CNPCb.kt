import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}

	val maxes = LongArray(n+1)
	maxes[0] = 1L

	if(array[0] > 1L){
		println(-1)
		return
	} else if(array[0] == 1L){
		maxes[0] = 0L
	}
	for(k in 1..n){
		maxes[k] = maxes[k-1]*2L - 2L*(array[k-1])
		if(maxes[k] < 0L){
			println(-1)
			return
		}
	}

	var answer = 0L
	var sum = 0L
	for(k in n downTo 0){
		sum += array[k]
		answer += min(sum,maxes[k])
	}

	println(answer)
}
