import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray().sorted()

		val dp = Array<IntArray>(n){IntArray(n*2+1){Int.MAX_VALUE}}

		//place first one somewhere
		for(k in 1..(n*2)){
			dp[0][k] = abs(array[0]-k)
		}

		for(k in 0 until n-1){
			for(j in 1..(n*2)){
				if(dp[k][j] == Int.MAX_VALUE) continue
				for(h in j+1..(n*2)){
					dp[k+1][h] = min(dp[k+1][h],dp[k][j] + abs(array[k+1]-h))
				}
			}
		}

		var answer = Int.MAX_VALUE
		for(k in 1..(n*2)){
			answer = min(answer,dp[n-1][k])
		}

		println(answer)

	}
}
