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

		val array = nums.map{Character.getNumericValue(it)}.toLongArray()
		var sum = 0L
		for(k in 0 until array.size){
			sum += array[k]
		}

		var answer = 0L
		val i = 0
		while(sum > s){
			//make ith digit from right 0
			answer += (10L-array[array.size-i-1])*pow10[i]
			sum = sum-array[array.size-1-1]+1
			array[array.size-i-2]++
		}
	}
}
