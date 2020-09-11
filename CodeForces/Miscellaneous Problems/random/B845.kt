import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val a = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

	val sum1 = a[0] + a[1] + a[2]
	val sum2 = a[3] + a[4] + a[5]

	if(sum1 == sum2) println(0)
	else{

		val a1 = IntArray(3){a[it]}.sorted()
		val a2 = IntArray(3){a[it+3]}.sorted()

		val changes = IntArray(6)
		//check 1
		if(sum1 > sum2){
			//get max 3
			for(k in 0 until 3){
				changes[k] = a1[k]
				changes[k+3] = 9-a2[k]
			}
		} else {
			for(k in 0 until 3){
				changes[k] = a2[k]
				changes[k+3] = 9-a1[k]
			}
		}

		changes.sort()

		val dif = Math.abs(sum1-sum2)

		var sumdif = 0
		var answer = 0
		var i = 5
		while(sumdif < dif){
			sumdif += changes[i]
			i--
			answer++
		}

		println(answer)
	}
}
