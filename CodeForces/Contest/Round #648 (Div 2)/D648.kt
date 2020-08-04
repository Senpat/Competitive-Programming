import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun in1(x : Int, y : Int, n : Int, m : Int) : Boolean{
		return x >= 0 && x < n && y >= 0 && y < m
	}

	fun check(board : Array<CharArray>, n : Int, m : Int) : Boolean{
		//draw box aroudn every b
		//and count G
		var numg = 0
		for(k in 0 until n){
			for(j in 0 until m){
				if(board[k][j] == 'G') numg++
				if(board[k][j] == 'B'){
					//draw box
					if(in1(k-1,j,n,m)){
						if(board[k-1][j] == 'G') return false
						if(board[k-1][j] != 'B'){
							board[k-1][j] = '#'
						}
					}
					if(in1(k+1,j,n,m)){
						if(board[k+1][j] == 'G') return false
						if(board[k+1][j] != 'B'){
							board[k+1][j] = '#'
						}
					}
					if(in1(k,j-1,n,m)){
						if(board[k][j-1] == 'G') return false
						if(board[k][j-1] != 'B'){
							board[k][j-1] = '#'
						}
					}
					if(in1(k,j+1,n,m)){
						if(board[k][j+1] == 'G') return false
						if(board[k][j+1] != 'B'){
							board[k][j+1] = '#'
						}
					}

				}
			}
		}


		//floodfill and count g's that you find
		val seen = Array(n){BooleanArray(m)}

		var findg = 0

		val q : Queue<Pair<Int,Int>> = LinkedList<Pair<Int,Int>>()

		if(board[n-1][m-1] == '#') return findg == numg

		q.add(Pair(n-1,m-1))
		seen[n-1][m-1] = true

		while(!q.isEmpty()){
			val p = q.poll()
			val x = p.first
			val y = p.second

			if(board[x][y] == 'G') findg++

			if(in1(x-1,y,n,m) && !seen[x-1][y] && board[x-1][y] != '#'){
				seen[x-1][y] = true
				q.add(Pair(x-1,y))
			}
			if(in1(x+1,y,n,m) && !seen[x+1][y] && board[x+1][y] != '#'){
				seen[x+1][y] = true
				q.add(Pair(x+1,y))
			}
			if(in1(x,y-1,n,m) && !seen[x][y-1] && board[x][y-1] != '#'){
				seen[x][y-1] = true
				q.add(Pair(x,y-1))
			}
			if(in1(x,y+1,n,m) && !seen[x][y+1] && board[x][y+1] != '#'){
				seen[x][y+1] = true
				q.add(Pair(x,y+1))
			}
		}

		return findg == numg
	}

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val board = Array(n){f.readLine().toCharArray()}

		if(check(board,n,m)){
			println("Yes")
		} else {
			println("No")
		}
	}
}
