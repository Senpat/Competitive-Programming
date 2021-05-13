import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val input = f.readLine().split(" ")
		val x = input[0].toInt()
		val y = input[1].toInt()
		val array = input[2].toCharArray()

		val n = array.size

		val inf = 1000000
		val dp = Array<IntArray>(n){IntArray(2){inf}}


		if(array[0] == 'C'){
			dp[0][0] = 0
		} else if(array[0] == 'J'){
			dp[0][1] = 0
		} else {
			dp[0][0] = 0
			dp[0][1] = 0
		}

		for(k in 1 until n){
			if(array[k] == 'C'){
				dp[k][0] = min(dp[k-1][0],dp[k-1][1]+y)
			} else if(array[k] == 'J'){
				dp[k][1] = min(dp[k-1][1],dp[k-1][0]+x)
			} else {
				//add C
				dp[k][0] = min(dp[k-1][0],dp[k-1][1]+y)
				//add J
				dp[k][1] = min(dp[k-1][1],dp[k-1][0]+x)
			}
		}

		val answer = min(dp[n-1][0],dp[n-1][1])
		println("Case #${q}: $answer")
	}
}
