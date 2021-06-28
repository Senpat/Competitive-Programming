import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))


	fun other(x : Char) : Char{
		if(x == 'R') return 'W'
		return 'R'
	}

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val board = Array<CharArray>(n){f.readLine().toCharArray()}

		var mod0 = '?'
		var mod1 = '?'

		var fail = false
		outer@for(k in 0 until n){
			for(j in 0 until m){
				if(board[k][j] != '.'){
					if((k+j)%2 == 0){
						if(mod0 == board[k][j]) continue
						else if(mod0 == other(board[k][j])){
							fail = true;
							break@outer
						} else {
							mod0 = board[k][j]
							mod1 = other(board[k][j])
						}
					} else {
						if(mod1 == board[k][j]) continue
						else if(mod1 == other(board[k][j])){
							fail = true;
							break@outer
						} else {
							mod1 = board[k][j]
							mod0 = other(board[k][j])
						}
					}
 				}
			}
		}

		if(mod0 == '?'){
			mod0 = 'R'
			mod1 = 'W'
		}

		if(fail){
			println("NO")
		} else {
			println("YES")
			val answer = Array<CharArray>(n){CharArray(m)}
			for(k in 0 until n){
				for(j in 0 until m){
					if((k+j)%2 == 0) answer[k][j] = mod0
					else answer[k][j] = mod1
				}
				println(answer[k].joinToString(""))
			}
		}


	}
}
