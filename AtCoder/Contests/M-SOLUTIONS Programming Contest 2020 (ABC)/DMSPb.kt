import java.io.*
import java.util.*
import kotlin.math.*
//WRONG
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val dp = LongArray(n){1000L}

	for(k in 0 until n){
		if(k > 0) dp[k] = max(dp[k],dp[k-1])
		val stocks = dp[k]/array[k]
		val remainder = dp[k]%array[k]

		for(j in k+1 until n){
			dp[j] = max(dp[j],stocks*array[j]+remainder)
		}
	}

	println(dp[n-1])
}
