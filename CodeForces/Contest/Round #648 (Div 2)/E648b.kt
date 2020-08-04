import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val pow2 = LongArray(61)
	pow2[0] = 1L
	for(k in 1..60){
		pow2[k] = pow2[k-1]*2L
	}

	fun getbits(xx : Long) : IntArray{
		var pow = 0
		val ret = IntArray(61)
		var x = xx
		while(x > 0){
			if(x and 1L == 1L){
				ret[pow]++
			}
			x = x shr 1
			pow++
		}

		return ret
	}
	fun calc1(add1 : IntArray) : Long{
		var answer = 0L
		for(k in 0..60){
			if(add1[k]>=1){
				answer += pow2[k]
			}
		}

		return answer
	}
	fun calc2(add1 : IntArray, add2 : IntArray) : Long{
		var answer = 0L
		for(k in 0..60){
			if(add1[k]+add2[k]>=1){
				answer += pow2[k]
			}
		}

		return answer
	}

	fun calc3(add1 : IntArray, add2 : IntArray, add3 : IntArray) : Long{
		var answer = 0L
		for(k in 0..60){
			if(add1[k]+add2[k]+add3[k]>=1){
				answer += pow2[k]
			}
		}

		return answer
	}

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()



	val bits = Array(n){j -> getbits(array[j])}



	var answer = 0L
	if(n > 0){
		for(k in 0 until n){
			answer = max(answer,array[k])
		}
	}
	if (n > 1){
		for(k in 0 until n){
			for(j in k+1 until n){
				answer = max(answer,array[k] or array[j])
			}
		}
	}
	if(n > 2){
		for(k in 0 until n){
			for(j in k+1 until n){
				for(h in j+1 until n){
					answer = max(answer,array[k] or array[j] or array[h])
				}
			}
		}
	}

	println(answer)

}
