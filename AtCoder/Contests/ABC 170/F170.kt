import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,x) = f.readLine().split(" ").map{it.toInt()}
	val (x1,y1,x2,y2) = f.readLine().split(" ").map{it.toInt()}

	fun in (a : Int, b : Int) : Boolean{
		return a >= 1 && a <= n && b >= 1 && b <= m
	}

	val board = Array(n+1){j ->
		if(j == 0) CharArray(m+1)
		else CharArray(1) + f.readLine().split(" ").map{it.toChar()}.toCharArray()
	}

	for(k in 1..n){
		println(board[k].joinToString(" "))
	}
	/*
	val minst = Array(n+1){Array(m+1){IntArray(4){Int.MAX_VALUE}}}

	val R = 0
	val U = 1
	val D = 2
	val L = 3

	val q = PriorityQueue<State>(compareBy{it.moves})
	minst[x1][y1] = IntArray(4){0}
	if(in(x1+1,y1) && board[x1+1][y1] != '@'){
		minst[x1+1][y1][0] = 1
		q.add(State(x1+1,y1,x1,y1,R,1))
	}
	if(in(x1,y1-1) && board[x1][y1-1] != '@'){
		minst[x1][y1-1][1] = 1
		q.add(State(x1,y1-1,x1,y1,U,1))
	}
	if(in(x1,y1+1) && board[x1][y1+1] != '@'){
		minst[x1][y1+1][2] = 1
		q.add(State(x1,y1+1,x1,y1,D,1))
	}
	if(in(x1-1,y1) && board[x1-1][y1] != '@'){
		minst[x1-1][y1][3] = 1
		q.add(State(x1-1,y1,x1,y1,L,1))
	}

	while(!q.isEmpty()){
		val s = q.poll()

		if(s.moves != minst[s.curx][s.cury][s.dir]){
			continue
		}

		if(in(s.curx+1,s.cury) && board[s.curx+1][s.cury] != '@'){
			if(s.dir == R){
				val newd = minst[s.lastturnx][s.lastturny].min()!! + (abs(s.curx+1 - s.lastturnx) + abs(s.cury - s.lastturny) + x-1)/x
				if(newd < minst[s.curx+1][s.cury][R]){
					minst[s.curx+1][s.cury][R] = newd
					q.add(State(s.curx+1,s.cury,s.lastturnx,s.lastturny,R,newd))
				}
			} else {
				val newd = minst[s.curx][s.cury].min()!! + 1
				if(newd < minst[s.curx+1][s.cury][R]){
					minst[s.curx+1][s.cury][R] = newd
					q.add(State(s.curx+1,s.cury,s.curx,s.cury,R,newd))
				}
			}
		}
		if(in(s.curx,s.cury-1) && board[s.curx][s.cury-1] != '@'){
			if(s.dir == U){
				val newd = minst[s.lastturnx][s.lastturny].min()!! + (abs(s.curx - s.lastturnx) + abs(s.cury-1 - s.lastturny) + x-1)/x
				if(newd < minst[s.curx][s.cury-1][U]){
					minst[s.curx][s.cury-1][U] = newd
					q.add(State(s.curx,s.cury-1,s.lastturnx,s.lastturny,U,newd))
				}
			} else {
				val newd = minst[s.curx][s.cury].min()!! + 1
				if(newd < minst[s.curx][s.cury-1][U]){
					minst[s.curx][s.cury-1][U] = newd
					q.add(State(s.curx,s.cury-1,s.curx,s.cury,U,newd))
				}
			}
		}
		if(in(s.curx,s.cury+1) && board[s.curx][s.cury+1] != '@'){
			if(s.dir == D){
				val newd = minst[s.lastturnx][s.lastturny].min()!! + (abs(s.curx - s.lastturnx) + abs(s.cury+1 - s.lastturny) + x-1)/x
				if(newd < minst[s.curx][s.cury+1][D]){
					minst[s.curx][s.cury+1][D] = newd
					q.add(State(s.curx,s.cury+1,s.lastturnx,s.lastturny,D,newd))
				}
			} else {
				val newd = minst[s.curx][s.cury+1].min()!! + 1
				if(newd < minst[s.curx][s.cury+1][D]){
					minst[s.curx][s.cury+1][D] = newd
					q.add(State(s.curx,s.cury+1,s.curx,s.cury,D,newd))
				}
			}
		}
		if(in(s.curx-1,s.cury) && board[s.curx-1][s.cury] != '@'){
			if(s.dir == L){
				val newd = minst[s.lastturnx][s.lastturny].min()!! + (abs(s.curx-1 - s.lastturnx) + abs(s.cury - s.lastturny) + x-1)/x
				if(newd < minst[s.curx-1][s.cury][L]){
					minst[s.curx-1][s.cury][L] = newd
					q.add(State(s.curx-1,s.cury,s.lastturnx,s.lastturny,L,newd))
				}
			} else {
				val newd = minst[s.curx][s.cury].min()!! + 1
				if(newd < minst[s.curx-1][s.cury][L]){
					minst[s.curx-1][s.cury][L] = newd
					q.add(State(s.curx-1,s.cury,s.curx,s.cury,L,newd))
				}
			}
		}

	}

	val answer = minst[x2][y2]!!.min()!!
	if(answer == Int.MAX_VALUE) println(-1)
	else println(answer)
	*/
}
data class State(val curx : Int, val cury : Int, val lastturnx : Int, val lastturny : Int, val dir : Int, val moves : Int)
