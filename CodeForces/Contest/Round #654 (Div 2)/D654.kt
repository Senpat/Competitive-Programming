import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val board = Array(n){IntArray(n){0}}


		if(m <= n){
			for(k in 0 until m){
				board[k][k] = 1
			}
		} else {

			for(k in 0 until n){
				board[k][k] = 1
			}

			var x = n
			var i = 1

			while(x < m){
				var add = 0

				while(i+add<n && x < m){
					board[i+add][add] = 1
					x++
					add++
				}

				var i2 = n-i
				var add2 = 0

				while(i2 + add2 < n && x < m){
					board[add2][i2+add2] = 1
					x++
					add2++
				}

				add = 0
				while(i+add<n && x < m){
					board[add][i+add] = 1
					x++
					add++
				}

				i2 = n-i
				add2 = 0
				while(i2 + add2 < n && x < m){
					board[i2+add2][add2] = 1
					x++
					add2++
				}

				i++
			}
		}


		var minrow = n
		var maxrow = 0
		var mincol = n
		var maxcol = 0

		for(k in 0 until n){
			var sumrow = 0
			var sumcol = 0

			for(j in 0 until n){
				sumrow += board[k][j]
				sumcol += board[j][k]
			}

			minrow = min(minrow,sumrow)
			maxrow = max(maxrow,sumrow)
			mincol = min(mincol,sumcol)
			maxcol = max(maxcol,sumcol)

		}

		val answer = ((maxrow-minrow).toLong())*((maxrow-minrow).toLong()) + ((maxcol-mincol).toLong())*((maxcol-mincol).toLong())

		println(answer)
		for(k in 0 until n){
			println(board[k].joinToString(""))
		}

	}
}
