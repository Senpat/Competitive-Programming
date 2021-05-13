import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

	val sum = array.sum()
	//println(sum)
	//check if there exists an initial partition
	val dp = Array<BooleanArray>(n){BooleanArray(sum+1){false}}
	dp[0][0] = true
	dp[0][array[0]] = true

	for(k in 0 until n-1){
		for(j in 0..sum){
			if(!dp[k][j]) continue
			//don't add
			dp[k+1][j] = true
			//add
			if(j+array[k+1] <= sum) dp[k+1][j+array[k+1]] = true
		}
	}
	/*
	for(k in 0 until n){
		println(dp[k].joinToString(" "))
	}*/

	if(sum%2 == 0 && dp[n-1][sum/2]){
		//has
		//get number with smallest power of 2
		var minindex = -5
		var min2 = 100
		for(k in 0 until n){
			var i = array[k]
			var count = 0
			while(i % 2 == 0){
				count++
				i = i shr 1
			}

			if(count < min2){
				minindex = k
				min2 = count
			}
		}
		println(1)
		println(minindex+1)
	} else {
		//doesn't have partition
		println(0)
	}


}
