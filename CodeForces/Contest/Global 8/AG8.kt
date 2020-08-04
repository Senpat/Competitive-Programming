import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (a,b,n) = f.readLine().split(" ").map{it.toInt()}

		var x1 = a
		var x2 = b
		var op = 0
		var y1 = b
		var y2 = a
		while(x1 <= n && x2 <= n && y1 <= n && y2 <= n){
			if(op % 2 == 0){
				x1 += x2
				y1 += y2
			} else {
				x2 += x1
				y2 += y1
			}

			op++
		}

		println(op)
	}
}
