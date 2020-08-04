import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		val sorted = IntArray(n){j -> array[j]}
		sorted.sort()

		val answer = mutableListOf<Int>()

		fun swap(x : Int){
			val temp = array[x+2]
			array[x+2] = array[x+1]
			array[x+1] = array[x]
			array[x] = temp
			answer.add(x+1)
		}

		for(k in n-1 downTo 2){
			//sort sorted[k] to k
			if(array[k] == sorted[k]) continue
			//find last unsorted sorted[k]
			var i = -1
			for(j in k downTo 0){
				if(array[j] == sorted[k]){
					i = j
					break
				}
			}

			for(j in i until k){
				if(j == 0) swap(0)
				else swap(j-1)
			}

		}

		if(array[0] != sorted[0] || array[1] != sorted[1]){
			println("-1")
		} else {
			println("${answer.size}")
			println(answer.joinToString(" "))
		}
	}
}
