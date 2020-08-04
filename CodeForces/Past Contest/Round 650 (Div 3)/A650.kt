import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val s = f.readLine()

		val sb = StringBuilder()
		sb.append(s[0])

		for(k in 1 until s.length step 2) sb.append(s[k])

		println(sb)
	}
}
