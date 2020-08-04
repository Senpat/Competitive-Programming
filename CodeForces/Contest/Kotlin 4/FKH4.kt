import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,q) = f.readLine().split(" ").map{it.toInt()}


	//ROW fenwick tree, to keep sum - 1 indexed
	val rowbits = LongArray(n+1)

	fun updaterow(ii : Int, x : Long){
		var i = ii
		while(i <= n){
			rowbits[i] += x
			i += (i and -i)
		}
	}

	fun psumrow(ii : Int): Long{
		var i = ii
		var sum : Long = 0L
		while(i > 0){
			sum += rowbits[i]
			i -= (i and -i)
		}
		return sum
	}

	//COLUMN fenwick tree, to keep sum - 1 indexed
	val colbits = LongArray(m+1)

	fun updatecol(ii : Int, x : Long){
		var i = ii
		while(i <= m){
			colbits[i] += x
			i += (i and -i)
		}
	}

	fun psumcol(ii : Int): Long{
		var i = ii
		var sum : Long = 0L
		while(i > 0){
			sum += colbits[i]
			i -= (i and -i)
		}
		return sum
	}

	val board = Array(n+1, {LongArray(m+1)})
	for(k in 1..n){
		val row = f.readLine().split(" ").map{it.toLong()}
		for(j in 1..m){
			board[k][j] = row[j-1]
			updatecol(j,board[k][j])
			updaterow(k,board[k][j])
		}
	}


	val initial = Array(n+1, {LongArray(m+1)})

	fun calcnextright(x : Int, y : Int, add : Long) : Long{
		return add + psumrow(x) - (psumrow(n)-psumrow(x))
	}
	fun calcnextleft(x : Int, y : Int, add : Long) : Long{
		return add + psumrow(n) - psumrow(x-1) - (psumrow(x-1))
	}
	fun calcnextdown(x : Int, y : Int, add : Long) : Long{
		return add + psumcol(y) - (psumcol(m)-psumcol(y))
	}
	fun calcnextup(x : Int, y : Int, add : Long) : Long{
		return add + psumcol(m) - psumcol(y-1) - (psumcol(y-1))
	}

	var first : Long = 0L
	for(k in 1..n){
		for(j in 1..m){
			first += ((k-1) + (j-1)).toLong() * board[k][j]
		}
	}

	initial[1][1] = first

	var min = first
	var minx = 1
	var miny = 1

	for(k in 1..n){
		for(j in 1..m){
			if(k <= n-1){
				//move to the right
				//println("${psumrow(k)} ${psumrow(n)}")
				//initial[k+1][j] = initial[k][j] + psumrow(k) - (psumrow(n)-psumrow(k))
				initial[k+1][j] = calcnextright(k,j,initial[k][j])
			}
			if(j <= m-1){
				//println("${psumcol(j)} ${psumcol(m)}")
				//initial[k][j+1] = initial[k][j] + psumcol(j) - (psumcol(m)-psumcol(j))
				initial[k][j+1] = calcnextdown(k,j,initial[k][j])
			}
			if(initial[k][j] < min){
				min = initial[k][j]
				minx = k
				miny = j
			}
			//println("$k $j ${initial[k][j]}")
		}
	}

	val sb = StringJoiner(" ")
	sb.add("$min")

	//queries
	for(t in 1..q){
		val (x,y,zi) = f.readLine().split(" ").map{it.toInt()}
		val z = zi.toLong()

		updaterow(x,z-board[x][y])
		updatecol(y,z-board[x][y])

		//try all four directions from minx and miny
		var curx = minx
		var cury = miny
		var curmin = min + (abs(x-minx) + abs(y-miny)).toLong() * (z-board[x][y])
		var found = false
		while(curx < n && calcnextright(curx,cury,curmin) < curmin){
			curmin = calcnextright(curx,cury,curmin)
			curx++
			found = true
			//println("$curmin row")
		}
		//try left
		if(!found){
			while(curx > 1 && calcnextleft(curx,cury,curmin) < curmin){
				curmin = calcnextleft(curx,cury,curmin)
				curx--
			}
		}

		found = false
		while(cury < m && calcnextdown(curx,cury,curmin) < curmin){
			curmin = calcnextdown(curx,cury,curmin)
			cury++
			found = true
			//println("$curmin col")
		}
		//try left
		if(!found){
			while(cury > 1 && calcnextup(curx,cury,curmin) < curmin){
				curmin = calcnextup(curx,cury,curmin)
				cury--
			}
		}

		min = curmin
		minx = curx
		miny = cury

		board[x][y] = z
		sb.add("$min")
	}






	println(sb)
}
