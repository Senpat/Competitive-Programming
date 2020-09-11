import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val board = Array(n){f.readLine().toCharArray()}

		var answer = 0
		for(k in 0 until n-1){
			if(board[k][m-1]=='R') answer++
		}

		for(k in 0 until m-1){
			if(board[n-1][k]=='D') answer++
		}

		println(answer)
	}
}
