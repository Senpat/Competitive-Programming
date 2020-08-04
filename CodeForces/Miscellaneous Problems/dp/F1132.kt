import java.io.*
import java.util.*
import kotlin.math.*
//tutorial
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().toCharArray()

	val dp = Array(n){IntArray(n){Int.MAX_VALUE}}

	fun calc(l : Int, r : Int) : Int{

		if(r < l) return 0
		if(l == r){
			dp[l][r] = 1
			return 1
		}
		if(dp[l][r] != Int.MAX_VALUE) return dp[l][r]

		var min = 1 + calc(l,r-1)
		for(k in l until r){
			if(array[k] == array[r]){
				min = min(min,calc(l,k) + calc(k+1,r-1))
			}
		}

		dp[l][r] = min
		return dp[l][r]
	}




	val answer = calc(0,n-1)
	/*
	for(k in 0 until n){
		println(dp[k].joinToString(" "))
	}*/
	println(answer)

}
