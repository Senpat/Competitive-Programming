import java.io.*
import java.util.*
import kotlin.math.*
//works on a1 version
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val a = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()
		val b = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()

		val answer = mutableListOf<Int>()

		for(k in n-1 downTo 0){
			if(a[k] == b[k]) continue
			if(a[0] == b[k]){
				//flip a[0]
				answer.add(1)
				a[0] = 1-a[0]
			}

			if(a[k] == b[k]) continue

			//flip first k
			answer.add(k+1)
			//invert all elements
			for(j in 0..k){
				a[j] = 1-a[j]
			}

			for(j in 0 until (k+1)/2){
				val temp = a[j]
				a[j] = a[k-j]
				a[k-j] = temp
			}

			//println("$q $k " + a.joinToString(""))
		}

		println("${answer.size} " + answer.joinToString(" "))
	}
}
