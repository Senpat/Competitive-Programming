import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (ni,m) = f.readLine().split(" ").map{it.toLong()}
		val array = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

		val n = ni.toInt()

		var i = 0
		for(k in 0 until n){
			if(array[k] == 0){
				//move to position i
				var dif = (k-i).toLong()
				if(dif <= m){
					m -= dif
					array[k] = 1
					array[i] = 0
					i++
				} else {
					array[k] = 1
					array[k-m.toInt()] = 0
					m = 0
				}
				if(m == 0L) break
			}
		}

		println(array.joinToString(""))
	}
}
