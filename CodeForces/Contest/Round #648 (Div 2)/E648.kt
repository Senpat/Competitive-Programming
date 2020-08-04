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

	fun calc0(curbits : IntArray, numadded :Int) : Long{
		val thresh = max(1,numadded-2)
		var answer = 0L
		for(k in 0..60){
			if(curbits[k] >= thresh){
				answer += pow2[k]
			}
		}

		return answer
	}

	fun calc1(curbits : IntArray, numadded :Int, add : IntArray) : Long{
		val thresh = max(1,numadded-1)
		var answer = 0L
		for(k in 0..60){
			if(curbits[k]+add[k] >= thresh){
				answer += pow2[k]
			}
		}

		return answer
	}

	fun calc2(curbits : IntArray, numadded :Int, add1 : IntArray, add2 : IntArray) : Long{
		val thresh = max(1,numadded)
		var answer = 0L
		for(k in 0..60){
			if(curbits[k]+add1[k]+add2[k]>= thresh){
				answer += pow2[k]
			}
		}

		return answer
	}

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()



	val bits = Array(n){j -> getbits(array[j])}

	//get max bit
	var maxbit = 0
	for(k in 0 until n){
		//get max bit
		var curmax = -1
		for(j in 60 downTo 0){
			if(bits[k][j] == 1){
				curmax = j
				break
			}
		}

		maxbit = max(maxbit,curmax)

	}

	//println(maxbit)

	val curbits = IntArray(61)
	var numadded = 0

	val notadded = mutableListOf<Int>()

	for(k in 0 until n){
		if(array[k] >= pow2[maxbit]){
			for(i in 0..60){
				if(bits[k][i] == 1){
					curbits[i]++
				}

			}
			numadded++
		} else {
			notadded.add(k)
		}
	}

	//println(numadded)

	var answer = calc0(curbits,numadded)
	if(notadded.size > 0){
		for(k in 0 until notadded.size){
			answer = max(answer,calc1(curbits,numadded,bits[notadded[k]]))
		}
	}
	if (notadded.size > 1){
		for(k in 0 until notadded.size){
			for(j in k+1 until notadded.size){
				answer = max(answer,calc2(curbits,numadded,bits[notadded[k]],bits[notadded[j]]))
			}
		}
	}

	println(answer)

}
