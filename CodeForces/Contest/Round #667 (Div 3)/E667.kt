import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val x = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()
		val y = IntArray(1) + f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

		val goback = IntArray(n+1){0}

		var front = 0

		for(k in 1..n){
			while(front < k && x[front+1] < x[k]-m){
				front++
			}
			goback[k] = front
		}

		val dp1 = IntArray(n+1){0}

		for(k in 1..n){
			dp1[k] = dp1[k-1]
			dp1[k] = max(dp1[k],k-goback[k])
		}

		val dp2 = IntArray(n+1){0}
		var answer = 0

		for(k in 1..n){
			answer = max(answer,k-goback[k]+dp1[max(0,goback[k])])
		}

		//println(goback.joinToString(" "))
		//println(dp1.joinToString(" "))

		println(answer)
	}
}
