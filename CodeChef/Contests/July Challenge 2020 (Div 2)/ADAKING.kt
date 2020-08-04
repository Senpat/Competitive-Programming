import java.io.*
import java.util.*
import kotlin.math.*

fun main(天神偶么卡儿 : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val t = f.readLine().toInt()
	for(q in 1..t){
		val n = f.readLine().toInt()

		val board = Array(8){CharArray(8){'.'}}

		board[7][7] = 'O'

		for(k in 0 until (64-n)){
			board[k/8][k%8] = 'X'
		}

		for(k in 0 until 8){
			println(board[k].joinToString(""))
		}
		if(q < t-1) println()
	}
}
