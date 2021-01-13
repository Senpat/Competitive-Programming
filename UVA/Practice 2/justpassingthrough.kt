import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (r,c,n) = f.readLine().split(" ").map{it.toInt()}
	val board = Array(r){f.readLine().split(" ").map{it.toInt()}.toIntArray()}

	val passes = Array(r){BooleanArray(c){false}}
	for(k in 1 until r-1){
		for(j in 1 until c-1){
			if((board[k+1][j] != -1 && board[k-1][j] != -1 && board[k][j+1] != -1 && board[k][j-1] != -1)){
				if(board[k+1][j] > board[k][j] && board[k-1][j] > board[k][j] && board[k][j+1] < board[k][j] && board[k][j-1] < board[k][j]){
					passes[k][j] = true
				}
			}
		}
	}

	val dp = Array(r){Array(c){IntArray(n+1){Int.MAX_VALUE}}}

	for(k in 0 until r){
		if(board[k][0] != -1){
			dp[k][0][0] = board[k][0]
		}
	}
	for(j in 0 until c-1){
		for(k in 0 until r){

			for(h in 0..n){
				if(dp[k][j][h] == Int.MAX_VALUE) continue
				if(k > 0){
					if(board[k-1][j+1] != -1){
						if(passes[k-1][j+1]){ if(h < n)
							dp[k-1][j+1][h+1] = min(dp[k-1][j+1][h+1],dp[k][j][h] + board[k-1][j+1])
						} else {
							dp[k-1][j+1][h] = min(dp[k-1][j+1][h],dp[k][j][h] + board[k-1][j+1])
						}
					}
				}
				if(board[k][j+1] != -1){
					if(passes[k][j+1]){ if (h < n)
						dp[k][j+1][h+1] = min(dp[k][j+1][h+1],dp[k][j][h] + board[k][j+1])
					} else {
						dp[k][j+1][h] = min(dp[k][j+1][h],dp[k][j][h] + board[k][j+1])
					}
				}
				if(k < r-1){
					if(board[k+1][j+1] != -1){
						if(passes[k+1][j+1]){ if(h < n)
							dp[k+1][j+1][h+1] = min(dp[k+1][j+1][h+1],dp[k][j][h] + board[k+1][j+1])
						} else {
							dp[k+1][j+1][h] = min(dp[k+1][j+1][h],dp[k][j][h] + board[k+1][j+1])
						}
					}
				}
			}

		}
	}

	var answer = Int.MAX_VALUE
	for(k in 0 until r){
		answer = min(answer,dp[k][c-1][n])
	}
	if(answer == Int.MAX_VALUE){
		println("impossible")
	} else {
		println(answer)
	}
}
