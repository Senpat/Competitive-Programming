import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,start) = f.readLine().split(" ").map{it.toInt()}

	val MAX = 20000000
	val dp = IntArray(n+1){MAX}

	dp[start] = 0
	for(k in 1..m){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}

		//a to b
		val newb = min(dp[a],dp[b]+1)


		//b to a
		val newa = min(dp[b],dp[a]+1)

		dp[b] = newb
		dp[a] = newa

		//println(dp.joinToString(" ").substring(9))
	}

	for(k in 1..n){
		if(dp[k] == MAX) dp[k] = -1
	}
	println(dp.joinToString(" ").substring(9))
}
