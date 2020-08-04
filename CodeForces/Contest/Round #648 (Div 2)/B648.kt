import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array1 = f.readLine().split(" ").map{it.toInt()}
		val array2 = f.readLine().split(" ").map{it.toInt()}

		// check sorted
		var sorted = true
		var has1 = false
		var has0 = false

		for(k in 0 until n){
			if(k > 0 && array1[k] < array1[k-1]) sorted = false
			if(array2[k] == 1) has1 = true
			if(array2[k] == 0) has0 = true
		}

		if(sorted || (has1 && has0)){
			println("Yes")
		} else {
			println("No")
		}

	}
}
