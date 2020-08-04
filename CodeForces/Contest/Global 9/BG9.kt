import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val board = Array(n){
			val st = StringTokenizer(f.readLine())
			IntArray(m){st.nextToken().toInt()}
		}

		val answer = Array(n){IntArray(m){0}}

		var fail = false
		for(k in 0 until n){
			for(j in 0 until m){
				if((k == 0 || k == n-1) && (j == 0 || j == m-1)) answer[k][j] = 2
				else if((k == 0 || k == n-1) || (j == 0 || j == m-1)) answer[k][j] = 3
				else answer[k][j] = 4

				if(answer[k][j] < board[k][j]) fail = true
			}
		}

		if(fail){
			println("NO")
		} else {
			println("YES")
			val sb = StringBuilder()
			for(k in 0 until n){
				for(j in 0 until m){
					sb.append("${answer[k][j]} ")
				}
				if(k < n-1)
					sb.appendln()
			}
			println(sb)
		}
	}
}
