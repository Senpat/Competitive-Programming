import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val s = f.readLine().toCharArray()
	val t = f.readLine().toCharArray()

	if(t[0] == t[1]){
		//make as much stuff t[0] as you can
		var not = 0L
		for(k in 0 until n){
			if(s[k] != t[0]) not++
		}

		var have = (n-not).toLong()
		have += min(m.toLong(),not)

		val answer = have*(have-1)/2L
		println(answer)
	} else {
		val dp = Array(n){Array(m+1){LongArray(n+1){-1L}}}
		if(s[0] == t[0]){
			dp[0][0][1] = 0L
		} else {
			dp[0][0][0] = 0L
			if(m > 0) dp[0][1][1] = 0L
		}

		for(k in 0 until n-1){
			for(j in 0..m){
				for(h in 0 until n){
					if(dp[k][j][h] == -1L) continue
					//don't change anything
					if(s[k+1]!=t[0] && s[k+1]!=t[1]){
						dp[k+1][j][h] = max(dp[k+1][j][h],dp[k][j][h])
					}
					//change next character to first character
					if(s[k+1] == t[0]){
						dp[k+1][j][h+1] = max(dp[k+1][j][h+1],dp[k][j][h])
					} else {
						if(j < m){
							dp[k+1][j+1][h+1] = max(dp[k+1][j+1][h+1],dp[k][j][h])
						}
					}

					//change next character to second character
					if(s[k+1] == t[1]){
						dp[k+1][j][h] = max(dp[k+1][j][h],dp[k][j][h]+h.toLong())
					} else {
						if(j < m){
							dp[k+1][j+1][h] = max(dp[k+1][j+1][h],dp[k][j][h]+h.toLong())
						}
					}
				}
			}
		}

		var answer = 0L
		for(j in 0..m){
			for(h in 0..n){
				answer = max(answer,dp[n-1][j][h])
			}
		}

		println(answer)


	}
}
