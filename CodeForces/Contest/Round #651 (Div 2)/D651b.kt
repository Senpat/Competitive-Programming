import java.io.*
import java.util.*
import kotlin.math.*
//semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toInt()}

	fun check(x : Int) : Boolean{

		val dp = Array(n){IntArray(2){0}}
		if(array[0] <= x){
			dp[0][0] = 1
		}
		dp[0][1] = 1

		for(k in 1 until n){
			if(array[k] <= x){
				dp[k][0] = max(dp[k-1][0],dp[k-1][1]+1)
			} else {
				dp[k][0] = dp[k-1][0]
			}
			dp[k][1] = dp[k-1][0]+1
		}

		return max(dp[n-1][0],dp[n-1][1]) >= m

	}

	var l = 1
	var r = 1000000000
	var ans = 1000000000

	while(l <= r){
		val mid = l + (r-l)/2

		if(check(mid)){
			r = mid-1
			ans = mid
		} else {
			l = mid+1
		}
	}

	println(ans)
}
