import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val board = Array(n){f.readLine().split(" ").map{it.toInt()}.toIntArray()}

		val rows = HashSet<Int>()
		val cols = HashSet<Int>()

		for(k in 0 until n){
			for(j in 0 until m){
				if(board[k][j] == 1){
					rows.add(k)
					cols.add(j)
				}
			}
		}

		val openrows = n-rows.size
		val opencols = m-cols.size

		val open = min(openrows,opencols)

		if(open%2 == 0){
			println("Vivek")
		} else {
			println("Ashish")
		}
	}
}
