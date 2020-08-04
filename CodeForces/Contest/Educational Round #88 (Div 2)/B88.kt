import java.io.*
import java.util.*
import kotlin.math.*
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (n,m,x,y) = f.readLine().split(" ").map{it.toInt()}

		y = min(y,2*x)

		val board = Array(n){f.readLine()}

		var answer = 0
		for(k in 0 until n){
			var j = 0
			while(j < m){
				if(board[k][j] == '*') j++
				else{
					if(j < m-1 && board[k][j+1] == '.'){
						answer += y
						j += 2
					} else {
						answer += x
						j++
					}

				}
			}
		}

		println(answer)
	}
}
