import java.io.*
import java.util.*
import kotlin.math.*
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	val dp = Array(n){IntArray(61){Int.MIN_VALUE}}

	for(k in 0 until n){
		dp[k][array[k]+30] = 0
	}

	var answer = 0
	for(k in 0 until n){
		for(j in 0..60){

			if(dp[k][j] == Int.MIN_VALUE) continue
			//print("$j ${dp[k][j]} ")
			answer = max(answer,dp[k][j])

			if(k == n-1) continue

			if(array[k+1] > j-30){
				//new max

				dp[k+1][array[k+1]+30] = max(dp[k+1][array[k+1]+30],dp[k][j]+j-30)

			} else {
				dp[k+1][j] = max(dp[k+1][j],dp[k][j]+array[k+1])
			}


		}
		//println()

	}

	println(answer)
}
