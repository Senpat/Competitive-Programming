import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val MOD : Long = 998244353L
	val MAX = 300005

	val pow2 = LongArray(MAX){0L}
	pow2[0] = 1L
	for(k in 1 until MAX){
		pow2[k] = (2L*pow2[k-1] + MOD)%MOD
	}

	val front = LongArray(MAX){0L}
	front[0] = 0L
	front[1] = 0L
	front[2] = 1L
	for(k in 3 until MAX){
		front[k] = ((pow2[k-2]-front[k-1])%MOD + MOD)%MOD
	}

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val board = Array<CharArray>(n){f.readLine().toCharArray()}

	var w = 0
	for(k in 0 until n){
		for(j in 0 until m){
			if(board[k][j] == 'o') w++
		}
	}

	var answer = 0L
	//rows
	for(k in 0 until n){
		var streak = 0
		for(j in 0 until m){
			if(board[k][j] == 'o') streak++
			else streak = 0
			if(streak >= 2){
				val add = (front[streak]*pow2[w-streak] + MOD)%MOD
				answer = (answer + add + MOD)%MOD
			}
		}

	}

	//columns
	for(j in 0 until m){
		var streak = 0
		for(k in 0 until n){
			if(board[k][j] == 'o') streak++
			else streak = 0
			if(streak >= 2){
				val add = (front[streak]*pow2[w-streak] + MOD)%MOD
				answer = (answer + add + MOD)%MOD
			}
		}
	}

	println(answer)



}
