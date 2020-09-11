import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,x,y) = f.readLine().split(" ").map{it.toInt()}

	val path = mutableListOf<Pair<Int,Int>>()

	path.add(Pair(x,y))

	//get first row
	for(k in 1..m){
		if(k != y) path.add(Pair(x,k))
	}

	var dir = -1
	for(k in 0 until n-1){
		val row = (x + k + n)%n+1

		if(dir == -1){
			for(j in m downTo 1){
				path.add(Pair(row,j))
			}
		} else {
			for(j in 1..m){
				path.add(Pair(row,j))
			}
		}
		dir = -dir
	}

	for(p in path){
		println("${p.first} ${p.second}")
	}
}
