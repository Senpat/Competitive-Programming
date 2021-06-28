import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun c2 (x : Long) : Long{
		return x*(x-1L)/2L
	}

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray()
		val n = array.size

		val vals = IntArray(n){j ->
			if(array[j] == '?') 0
			else if((j%2 == 0 && array[j] == '0') || (j%2 == 1 && array[j] == '1')) 1				//010101...
			else 2																										//101010...
		}

		//1s
		var count1 : Long = 0L

		var curlen : Long = 0L
		for(k in 0 until n){
			if(vals[k] == 2){
				count1 += c2(curlen+1)
				curlen = 0
			} else {
				curlen++
			}
		}

		count1 += c2(curlen+1)

		//2s
		var count2 : Long = 0L
		curlen = 0L
		for(k in 0 until n){
			if(vals[k] == 1){
				count2 += c2(curlen+1)
				curlen = 0
			} else {
				curlen++
			}
		}

		count2 += c2(curlen+1)

		//0s
		var count0 : Long = 0L
		curlen = 0L
		for(k in 0 until n){
			if(vals[k] != 0){
				count0 += c2(curlen+1)
				curlen = 0
			} else {
				curlen++
			}
		}

		count0 += c2(curlen+1)

		val answer = count1 + count2 - count0
		println(answer)


	}
}
