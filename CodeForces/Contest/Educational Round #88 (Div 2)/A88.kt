import java.io.*
import java.util.*
import kotlin.math.*
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,j,m) = f.readLine().split(" ").map{it.toInt()}

		val cpp = n/m		//cards per player

		val max = min(cpp,j)

		val remainingj = j-max

		var max2 = remainingj/(m-1)

		if(remainingj % (m-1) != 0) max2++

		println(max-max2)
	}
}
