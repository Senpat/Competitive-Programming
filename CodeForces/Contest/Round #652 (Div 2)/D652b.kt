import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 1000000007L

	val dp = LongArray(2000005)
	val dptop = LongArray(2000005)
	dp[1] = 0L
	dp[2] = 0L
	dptop[3] = 4L
	dp[4] = 4L
	dptop[4] = 4L

	for(k in 4 until 2000005){
		dp[k] = ((2L*max(dptop[k-2],dp[k-2]) + MOD)%MOD + max(dptop[k-1],dp[k-1]) + MOD)%MOD
		dptop[k] = ((2L*dp[k-2] + MOD)%MOD + dp[k-1] + 4L + MOD)%MOD
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		println(max(dptop[n],dp[n]))
	}
}
