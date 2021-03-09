import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val b = f.readLine().toCharArray().map{Character.getNumericValue(it)}

		val a = IntArray(n){0}
		var last = -1
		for(k in 0 until n){
			if(b[k] == 1){
				if(last == 2){
					last = 1
					a[k] = 0
				} else {
					a[k] = 1
					last = 2
				}
			} else {
				if(last == 1){
					last = 0
					a[k] = 0
				} else {
					last = 1
					a[k] = 1
				}
			}
		}

		println(a.joinToString(""))
	}
}
