import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

		var sum = 0L
		for(k in 0 until n step 2){
			sum += array[k]
		}

		val after = mutableListOf<Long>()
		for(k in 0 until n-1 step 2){
			after.add(array[k+1]-array[k])
		}

		val before = mutableListOf<Long>()
		for(k in 2 until n step 2){
			before.add(array[k-1]-array[k])
		}

		var answerafter = 0L
		var answerbefore = 0L

		if(after.size > 0){

			var maxafter = after[0]
			var curmax = after[0]

			for(k in 1 until after.size){
				curmax = max(after[k],curmax+after[k])
				maxafter = max(maxafter,curmax)
			}

			answerafter = maxafter
		}

		if(before.size > 0){
			var maxbefore = before[0]
			var curmax = before[0]
			for(k in 1 until before.size){
				curmax = max(before[k],curmax+before[k])
				maxbefore = max(maxbefore,curmax)
			}

			answerbefore = maxbefore
		}
		val answer = sum + max(0,max(answerafter,answerbefore))

		println(answer)

	}
}
