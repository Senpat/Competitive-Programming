import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,x) = f.readLine().split(" ").map{it.toInt()}

	val board = Array(n){f.readLine().toCharArray()}

	var start = 0
	for(k in 0 until n){
		for(j in 0 until m){
			if(board[k][j] == '#') start++
		}
	}

	var answer = 0
	for(k in 0 until (1 shl n)){
		for(j in 0 until (1 shl m)){
			val cur = Array(n){r -> CharArray(m){c -> board[r][c]}}
			var cursum = start
			var ni = 0
			while(ni <= 6){
				if(k and (1 shl ni) == (1 shl ni)){
					for(h in 0 until m){
						if(cur[ni][h] == '#'){
							cur[ni][h] = '.'
							cursum--
						}
					}
				}

				if(j and (1 shl ni) == (1 shl ni)){
					for(h in 0 until n){
						if(cur[h][ni] == '#'){
							cur[h][ni] = '.'
							cursum--
						}
					}
				}
				ni++
			}
			if(cursum == x) answer++
		}


	}

	println(answer)
}
