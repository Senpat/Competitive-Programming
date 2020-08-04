import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val board = Array<IntArray>(n){f.readLine().split(" ").map{it.toInt()}.toIntArray()}

		val total = n+m-2
		val freq = Array<IntArray>(total+1){arrayOf(0,0).toIntArray()}

		for(k in 0 until n){
			for(j in 0 until m){
				freq[k+j][board[k][j]]++
			}
		}

		var answer = 0
		for(k in 0 until (total+1)/2){
			answer += min(freq[k][0] + freq[total-k][0],freq[k][1] + freq[total-k][1])
		}
		println(answer)

	}
}
