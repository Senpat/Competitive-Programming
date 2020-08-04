import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()
		val n = array.size
		//most frequent
		val freq = IntArray(10){0}

		var maxi = 0
		for(k in 0 until n){
			freq[array[k]]++
			if(freq[maxi] <= freq[array[k]]){
				maxi = array[k]
			}
		}

		var answer = n-freq[maxi]

		for(k in 0..9){
			for(j in 0..9){
				if(k==j) continue
				var first = true
				var curcount = 0
				for(h in 0 until n){
					if(first){
						if(array[h] == k){
							curcount++
							first = false
						}
					} else {
						if(array[h] == j){
							curcount++
							first = true
						}
					}
				}

				if(curcount % 2 == 1) curcount--
				answer = min(answer,n-curcount)
			}
		}

		println(answer)


	}
}
