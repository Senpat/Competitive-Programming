import java.io.*
import java.util.*
import kotlin.math.*
//trop complique
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val pow10 = LongArray(19){0L}
	pow10[0] = 1
	for(k in 1 until 19){
		pow10[k] = pow10[k-1]*10L
	}

	for(q in 1..f.readLine().toInt()){
		val (nums,ss) = f.readLine().split(" ")
		val n = nums.toLong()
		val s = ss.toLong()

		val array = nums.toCharArray().map{Character.getNumericValue(it).toLong()}.toLongArray()

		var curnumber = 0L
		var sum = 0L
		var answer = Long.MAX_VALUE
		for(k in array.size-1 downTo 0){
			val cur = n-(curnumber + (10-array[k]+1)*pow10[k])
			sum += array[k]
			if(sum+1 <= s){
				answer = min(answer,cur)
			}

			curnumber += array[k]*pow10[k]
		}

		println(answer)


	}
}
