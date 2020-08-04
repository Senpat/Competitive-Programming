import java.io.*
import java.util.*
import kotlin.math.*
//WRONG
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val array = LongArray(1) + f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val dp = Array(n+1){LongArray(2){0L}}

	dp[0][0] = 0L
	dp[0][1] = 1000L

	for(k in 1..n){
		dp[k][0] = max(dp[k-1][0],dp[k-1][1]/array[k])
		dp[k][1] = max(dp[k-1][1],dp[k-1][0]*array[k])
	}

	val answer = max(dp[n][1],dp[n][0]*array[n])
	println(answer)
}
