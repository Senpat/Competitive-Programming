import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val zeros = IntArray(20000005)
	for(k in 1 until 20000005){
		var dig = 0			//digits in binary form
		var i = k
		while (i > 0){
			i /= 2
			dig++
		}
		zeros[k] = k-dig
	}



	for(一个 in 1..f.readLine().toInt()){
		val array = f.readLine().map{Character.getNumericValue(it)}.toIntArray()
		val n = array.size

		//println(array.joinToString(" "))
		var answer = 0L
		for(k in 0 until n){
			if(array[k] == 0) continue
			//find number of leading zeros
			var i = k-1
			var nz = 0
			while(i >= 0 && array[i] == 0){
				i--
				nz++
			}

			var num = 1
			answer++					//1
			i = k+1
			while(i < n && zeros[(num shl 1) or array[i]] <= nz){
				//println("$k $num")
				num = (num shl 1) or array[i]
				answer++
				i++

			}
		}
		println(answer)
	}


}
