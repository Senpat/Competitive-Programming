import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val board = Array(n){IntArray(n){0}}

		for(k in 0 until n){
			board[k][k] = 1
			if(k < n-1) board[k+1][k] = 1
		}
		board[0][n-1] = 1

		for(k in 0 until n){
			println(board[k].joinToString(" "))
		}
	}
}
