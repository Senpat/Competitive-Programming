import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val NULL = Int.MIN_VALUE

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	if(n == 1){
		println("YES")
		println("0")
		return
	}

	val dp = Array<IntArray>(n){IntArray(2){NULL}}
	//[i][x] -> x = 0, put ith in dec, store min possible max num in inc seq.
   //x = 1, put ith in inc, store max possible min num in dec seq
	dp[0][0] = -1
	dp[0][1] = Int.MAX_VALUE

	val par = Array<IntArray>(n){IntArray(2){NULL}}

	for(k in 1 until n){
		if(array[k] > array[k-1]){
			//set inc
			var curpar = NULL
			var max = -1
			if(dp[k-1][0] != NULL && array[k] > dp[k-1][0]){
				max = array[k-1]
				curpar = 0
			}
			if(dp[k-1][1] != NULL){
				if(dp[k-1][1] > max){
					max = dp[k-1][1]
					curpar = 1
				}
			}

			if(curpar != NULL){
				dp[k][1] = max
				par[k][1] = curpar
			}
		}

		if(array[k] >= array[k-1]){
			//set dec
			if(dp[k-1][1] != NULL && array[k] < dp[k-1][1]){
				dp[k][0] = array[k-1]
				par[k][0] = 1
			}
		}

		if(array[k] < array[k-1]){
			//set dec
			var curpar = NULL
			var min = Int.MAX_VALUE
			if(dp[k-1][1] != NULL && array[k] < dp[k-1][1]){
				min = array[k-1]
				curpar = 1
			}
			if(dp[k-1][0] != NULL){
				if(dp[k-1][0] < min){
					min = dp[k-1][0]
					curpar = 0
				}
			}

			if(curpar != NULL){
				dp[k][0] = min
				par[k][0] = curpar
			}
		}

		if(array[k] <= array[k-1]){
			//set inc
			if(dp[k-1][0] != NULL && array[k] > dp[k-1][0]){
				dp[k][1] = array[k-1]
				par[k][1] = 0
			}
		}
	}

	val answer = IntArray(n)

	var x = -1
	if(par[n-1][0] != NULL) x = 0
	if(par[n-1][1] != NULL) x = 1

	if(x == -1){
		println("NO")
	} else {
		for(k in n-1 downTo 0){
			answer[k] = 1-x
			x = par[k][x]
		}
		println("YES")
		println(answer.joinToString(" "))
	}
/*
	for(k in 0 until n){
		print("${dp[k][0]} ")
	}
	println()
	for(k in 0 until n){
		print("${dp[k][1]} ")
	}
	println()
	*/


}
