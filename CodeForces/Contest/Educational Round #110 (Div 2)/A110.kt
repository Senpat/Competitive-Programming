import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (a,b,c,d) = f.readLine().split(" ").map{it.toInt()}

		val min1 = min(a,b)
		val max1 = max(a,b)
		val min2 = min(c,d)
		val max2 = max(c,d)

		if(min1 > max2 || min2 > max1){
			println("NO")
		} else {
			println("YES")
		}
	}
}
