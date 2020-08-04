import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}
		var sum = array.map{max(0,it)}.sum()

		val answer = Array(n){j -> if(array[j] > 0) 1 else 0}

		var minpos = 10001
		var minpindex = -1

		var maxneg = 10001
		var maxnindex = -1
		for(k in 0 until n){
			if(array[k] > 0){
				if(minpos > array[k]){
					minpos = array[k]
					minpindex = k
				}
			} else if(array[k] < 0){
				if(maxneg > abs(array[k])){
					maxneg = abs(array[k])
					maxnindex = k
				}
			}
		}

		if(minpindex != -1 && minpos <= maxneg){
			sum -= minpos
			answer[minpindex] = 0
		} else if(maxnindex != -1 && maxneg < minpos){
			sum -= maxneg
			answer[maxnindex] = 1
		}

		println(sum)
		println(answer.joinToString(""))



	}
}
