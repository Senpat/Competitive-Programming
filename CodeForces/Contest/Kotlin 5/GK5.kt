import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()

	val psum = LongArray(n+1){0L}
	psum[0] = 0L
	for(k in 1..n){
		psum[k] = psum[k-1]+array[k-1]
	}

	val dp = Array<LongArray>(n){LongArray(m+1){0L}}

	dp[0][0] = 0L
	for(k in 1..m){
		if(k*2-1 >= n) continue
		dp[k*2-1][k] = (psum[k*2]-psum[k]) - (psum[k]-psum[0])
	}

	for(k in 0 until n-1){
		for(j in 0..m){
			dp[k+1][j] = max(dp[k+1][j],dp[k][j])

			for(h in 1..m){
				if(j+h > m) break
				if(k + 2*h >= n) break
				dp[k + 2*h][j+h] = max(dp[k + 2*h][j+h],dp[k][j] + (psum[k+2*h+1] - psum[k+h+1]) - (psum[k+h+1] - psum[k+1]))
			}
		}
	}

	var max = 0L
	for(k in 0..m){
		max = max(max,dp[n-1][k])
	}

	println(max)
}
