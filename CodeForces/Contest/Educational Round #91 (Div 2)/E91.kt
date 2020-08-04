import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val starting = f.readLine().split(" ").map{it.toInt()}

	val sets = Array(m+1){HashSet<Int>()}

	for(k in 0 until n){
		sets[starting[k]].add(k+1)
	}

	val answer = mutableListOf<Int>()

	var count = 0
	for(k in 1..m){
		for(i in sets[k]){
			if(!sets[k].contains(i-1)) count++
		}
	}

	answer.add(count)

	fun combine(x : Int, y : Int){

		var prev = answer[answer.size-1]

		if(sets[x].size >= sets[y].size){
			//add y to x

			//how many of y contributes to answer
			var cont = 0
			for(i in sets[y]){
				if(!sets[y].contains(i-1)) cont++
			}

			prev-=cont

			for(i in sets[y]){
				if(sets[x].contains(i-1) && sets[x].contains(i+1)) prev--
				else if(!sets[x].contains(i-1) && !sets[x].contains(i+1)) prev++
				sets[x].add(i)
			}

		} else {
			//add x to y, then move pointers

			//how many of x contributes to answer
			var cont = 0
			for(i in sets[x]){
				if(!sets[x].contains(i-1)) cont++
			}

			prev-=cont

			for(i in sets[x]){
				if(sets[y].contains(i-1) && sets[y].contains(i+1)) prev--
				else if(!sets[y].contains(i-1) && !sets[y].contains(i+1)) prev++
				sets[y].add(i)
			}

			sets[x] = sets[y]
			sets[y] = HashSet<Int>()

		}

		answer.add(prev)
	}

	for(q in 1 until m){
		val (x,y) = f.readLine().split(" ").map{it.toInt()}

		combine(x,y)
	}

	val sb = StringBuilder()
	for(k in 0 until m){
		sb.appendln(answer[k]-1)
	}

	println(sb)
}
