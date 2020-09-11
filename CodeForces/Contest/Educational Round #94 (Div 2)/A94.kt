import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine()
		val sb = StringBuilder()

		for(k in 0 until 2*n-1 step 2) sb.append(array[k])

		println(sb)
	}
}
