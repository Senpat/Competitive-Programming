import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m,z) = f.readLine().split(" ").map{it.toInt()}

		val array = f.readLine().split(" ").map{it.toInt()}

		val dp = Array(n){IntArray(z+1){0}}

		dp[0][0] = array[0]
		var answer = array[0]

		for(j in 0..z){
			for(k in 0 until n){
				if(k + 2*j > m) continue
				if(dp[k][j] == 0) continue

				answer = max(answer,dp[k][j])

				if(k > 0 && k+2*j == m-1 && j < z){
					//go back and stop
					answer = max(answer,dp[k][j] + array[k-1])
				} else if(k > 0 && j < z){
					//go back and forth
					dp[k][j+1] = max(dp[k][j+1],dp[k][j] + array[k-1] + array[k])
				}

				//go forward
				if(k < n-1){
					dp[k+1][j] = max(dp[k+1][j],dp[k][j] + array[k+1])
				}
			}
		}

		println(answer)
	}
}
