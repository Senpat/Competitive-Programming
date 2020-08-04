import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	val MAX = 1000000
	val answer = IntArray(n){MAX}

	if(array[0] == 1){
		answer[0] = 0
	} else if(array[0] > 1){
		println(-1)
		return
	}

	for(k in 0 until n-1){
		if(array[k] != array[k+1]){
			answer[k+1] = array[k]
		}
	}

	var i = 0

	for(k in 1 until n){
		if(array[k] > array[k-1]+1){
			//get every number in [array[k-1]+1,array[k])
			var x = array[k-1]+1
			while(i < n && x < array[k]){
				if(answer[i] != MAX){
					i++
				} else {
					answer[i] = x
					i++
					x++
				}
			}

			if(x != array[k]){
				println(-1)
				return
			}
		}

	}

	println(answer.joinToString(" "))
}
