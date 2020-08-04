import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()

		var first1 = -1
		var last0 = -1
		for(k in 0 until n){
			if(array[k] == 1 && first1 == -1){
				first1 = k
			} else if(array[k] == 0){
				last0 = k
			}
		}

		if(first1 == -1 || last0 == -1 || first1 > last0){
			println(array.joinToString(""))
		} else {
			val sj = StringJoiner("")
			for(k in 0 until first1){
				sj.add("${array[k]}")
			}
			sj.add("0")
			for(k in (last0+1) until n){
				sj.add("${array[k]}")
			}
			println(sj)
		}
	}
}
