import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val board = Array(n){f.readLine().toCharArray()}

		var answer = 0

		val dp = Array(n){IntArray(m){0}}
		for(k in 0 until n){
			for(j in 0 until m){
				if(board[k][j] == '*'){
					answer++
					dp[k][j] = 1
				}
			}
		}

		for(i in 2..n){
			for(k in 1 until n){
				for(j in 1 until m-1){
					if(board[k][j] == '*' && dp[k-1][j] >= i-1 && dp[k][j-1] >= i-1 && dp[k][j+1] >= i-1){
						dp[k][j] = i
						answer++
					}
				}
			}
		}

		//for(k in 0 until n) println(dp[k].joinToString(" "))

		println(answer)
	}
}
