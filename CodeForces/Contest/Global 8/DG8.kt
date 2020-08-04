import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}

	val freq = IntArray(21)

	for(k in 0 until n){
		var i = array[k]
		var p = 0
		while(i > 0){
			if(i and 1L == 1L){
				freq[p]++
			}
			i = i shr 1
			p++
		}
	}

	val max = freq.max()!!

	var answer = 0L
	for(k in 1..max){
		var sum = 0L
		for(j in 0..20){
			if(freq[j] >= k) sum += 1L shl j
		}
		answer += sum*sum
	}

	println(answer)
}
