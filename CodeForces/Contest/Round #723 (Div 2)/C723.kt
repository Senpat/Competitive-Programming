import java.io.*
import java.util.*
import kotlin.math.*
//solve first subtask
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray()

	val dp = Array<LongArray>(n){LongArray(n+1){-1L}}

	if(array[0] >= 0L) dp[0][1] = array[0]
	else dp[0][0] = 0L

	for(k in 0 until n-1){
		for(j in 0 until n){
			if(dp[k][j] == -1L) continue

			//add
			if(dp[k][j] + array[k+1] > dp[k+1][j+1]) dp[k+1][j+1] = dp[k][j] + array[k+1]

			//don't add
			dp[k+1][j] = max(dp[k+1][j],dp[k][j])
		}
	}

	var answer = 0
	for(k in 0..n){
		if(dp[n-1][k] >= 0) answer = k
	}

	println(answer)

}
