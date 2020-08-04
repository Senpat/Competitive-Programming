import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD = 998244353L

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toInt()}

	val dp = Array(n, {LongArray(m+1)})

	dp[0][0] = 2L
	if(array[0] <= m) dp[0][array[0]] = 1L

	for(k in 0 until n-1){
		for(j in 0..m){
			if(dp[k][j] == 0L) continue
			dp[k+1][j] = (dp[k+1][j] + ((dp[k][j]*2 + MOD)%MOD) + MOD)%MOD
			if(j+array[k+1] <= m){
				dp[k+1][j+array[k+1]] = (dp[k+1][j+array[k+1]] + dp[k][j] + MOD)%MOD
			}
		}
	}

	println(dp[n-1][m])

}
