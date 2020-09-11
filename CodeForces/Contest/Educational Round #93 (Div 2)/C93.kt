import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)-1}.toIntArray()

		val hmap = HashMap<Int,Long>()
		hmap[0] = 1L

		var psum = 0

		var answer = 0L
		for(k in 0 until n){
			psum += array[k]

			answer += (hmap[psum] ?: 0L)

			hmap[psum] = (hmap[psum] ?: 0L) + 1L
		}

		println(answer)
	}
}
