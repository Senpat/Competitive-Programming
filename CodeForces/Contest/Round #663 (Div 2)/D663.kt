import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (N,M) = f.readLine().split(" ").map{it.toInt()}
	val boardin = Array(N){f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()}

	if(min(N,M) == 1) println(0)
	else if(min(N,M) == 2 || min(N,M) == 3){
		val n = max(N,M)
		val m = min(N,M)

		val board = Array(n){IntArray(m){0}}
		if(n != N){
			for(k in 0 until n){
				for(j in 0 until m){
					board[k][j] = boardin[j][k]
				}
			}
		} else {
			for(k in 0 until n){
				for(j in 0 until m){
					board[k][j] = boardin[k][j]
				}
			}
		}

		if(m == 2){
			val dp = Array(n){IntArray(2){0}}
			val start = (board[0][0] + board[0][1]+2)%2
			dp[0][start] = 0
			dp[0][1-start] = 1

			for(k in 1 until n){
				val cur = (board[k][0] + board[k][1]+2)%2
				dp[k][cur] = dp[k-1][1-cur]
				dp[k][1-cur] = dp[k-1][cur]+1
			}

			println(min(dp[n-1][0],dp[n-1][1]))
		} else {
			//m==3

			val dp = Array(n){Array(2){IntArray(2){0}}}
			val start1 = (board[0][0] + board[0][1] + 2)%2
			val start2 = (board[0][1] + board[0][2] + 2)%2

			dp[0][start1][start2] = 0
			dp[0][start1][1-start2] = 1
			dp[0][1-start1][start2] = 1
			dp[0][1-start1][1-start2] = 1

			for(k in 1 until n){
				val cur1 = (board[k][0] + board[k][1] + 2)%2
				val cur2 = (board[k][1] + board[k][2] + 2)%2

				dp[k][cur1][cur2] = dp[k-1][1-cur1][1-cur2]
				dp[k][1-cur1][cur2] = dp[k-1][cur1][1-cur2]+1
				dp[k][cur1][1-cur2] = dp[k-1][1-cur1][cur2]+1
				dp[k][1-cur1][1-cur2] = dp[k-1][cur1][cur2]+1
			}


			println(min(min(dp[n-1][0][0],dp[n-1][0][1]),min(dp[n-1][1][0],dp[n-1][1][1])))
		}


	} else {
		println(-1)
	}
}
