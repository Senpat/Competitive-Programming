import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (r,g,b) = f.readLine().split(" ").map{it.toInt()}

	val rs = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()
	val gs = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()
	val bs = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()

	val dp = Array(r+1){Array(g+1){LongArray(b+1){0L}}}

	dp[r][g][b] = 0L

	var answer = 0L

	for(k in r downTo 0){
		for(j in g downTo 0){
			for(h in b downTo 0){
				if((k < r || j < g || h < b) && dp[k][j][h] == 0L) continue

				answer = max(answer,dp[k][j][h])

				if(k > 0 && j > 0) dp[k-1][j-1][h] = max(dp[k-1][j-1][h],dp[k][j][h] + rs[k-1]*gs[j-1])
				if(k > 0 && h > 0) dp[k-1][j][h-1] = max(dp[k-1][j][h-1],dp[k][j][h] + rs[k-1]*bs[h-1])
				if(j > 0 && h > 0) dp[k][j-1][h-1] = max(dp[k][j-1][h-1],dp[k][j][h] + gs[j-1]*bs[h-1])
			}
		}
	}

	println(answer)
}
