import java.io.*
import java.util.*
import kotlin.math.*
//INTERPRETED THE PROBLEM WRONG
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toLong()}

	val ands = Array(n){LongArray(n)}

	fun calc(a : Int, b : Int) : Long{
		var answer = array[a]
		for(k in (a+1)..b){
			answer = answer and array[k]
		}
		return answer
	}

	for(k in 0 until n){
		for(j in k until n){
			ands[k][j] = calc(k,j)
		}
	}

	val dp = Array(n){LongArray(m+1){-1L}}

	//get initial
	for(k in 0 until n){
		dp[k][1] = ands[0][k]
	}

	for(k in 0 until n-1){
		for(j in 0 until m){
			if(dp[k][j] == -1L) continue
			//add from k+1
			for(h in k+1 until n){
				dp[h][j+1] = max(dp[h][j+1],dp[k][j]+ands[k+1][h])
			}

		}
	}

	println(dp[n-1][m])



}
