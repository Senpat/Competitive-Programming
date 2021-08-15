import java.io.*
import java.util.*
import kotlin.math.*
//COCI '15 Contest 2 #3 Artur
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val lines = Array<Pair<Pair<Int,Int>,Pair<Int,Int>>>(1){Pair(Pair(0,0),Pair(0,0))} +
		Array<Pair<Pair<Int,Int>,Pair<Int,Int>>>(n){
		val (a,b,c,d) = f.readLine().split(" ").map{it.toInt()}

		//first point is left point
		if(a < c) Pair(Pair(a,b),Pair(c,d))
		else Pair(Pair(c,d),Pair(a,b))
	}

	fun below(x1 : Int, y1 : Int, x2 : Int, y2 : Int, xA : Int, yA : Int) : Boolean{
		val v1x = x2-x1
		val v1y = y2-y1
		val v2x = xA-x1
		val v2y = yA-y1

		return (v1x*v2y - v2x*v1y) < 0
	}

	//returns if line a is below line b
	fun interfere(a : Pair<Pair<Int,Int>,Pair<Int,Int>>, b : Pair<Pair<Int,Int>,Pair<Int,Int>>) : Boolean{
		val ax1 = a.first.first
		val ay1 = a.first.second
		val ax2 = a.second.first
		val ay2 = a.second.second

		val bx1 = b.first.first
		val by1 = b.first.second
		val bx2 = b.second.first
		val by2 = b.second.second

		//line b, point a1
		if(ax1 >= bx1 && ax1 <= bx2) return below(bx1,by1,bx2,by2,ax1,ay1)

		//line b, point a2
		if(ax2 >= bx1 && ax2 <= bx2) return below(bx1,by1,bx2,by2,ax2,ay2)

		//line a, point b1 (only happens if a surrounds b)
		if(bx1 >= ax1 && bx1 <= ax2) return !below(ax1,ay1,ax2,ay2,bx1,by1)

		//line a, point b2
		if(bx2 >= ax1 && bx2 <= ax2) return !below(ax1,ay1,ax2,ay2,bx2,by2)

		//don't cross each other
		return false
	}

	val adj = Array(n+1){mutableListOf<Int>()}

	for(k in 1..n){
		for(j in 1..n){
			if(k == j) continue
			if(interfere(lines[k],lines[j])){
				adj[k].add(j)
			}
		}
	}

	val stack = Stack<Int>()

	val seen = HashSet<Int>()

	fun dfs(v : Int){
		seen.add(v)
		for(nei in adj[v]){
			if(seen.contains(nei)) continue
			dfs(nei)
		}

		stack.add(v)
	}

	for(k in 1..n){
		if(seen.contains(k)) continue
		dfs(k)
	}

	val answer = IntArray(n){stack.pop()}
	println(answer.joinToString(" "))

}
