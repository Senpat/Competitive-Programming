import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

		val n = array.size

		var num0 = 0
		var num1 = 0

		for(k in 0 until n){
			if(array[k] == 0) num0++
			else num1++
		}

		if(min(num0,num1) % 2 == 0){
			println("NET")
		} else {
			println("DA")
		}
	}
}
