import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,q) = f.readLine().split(" ").map{it.toInt()}

	val bits = IntArray(n+5)

	fun update(ii : Int, x : Int){
		var i = ii
		while(i <= n){
			bits[i] += x
			i += (i and -i)
		}
	}

	fun psum(ii : Int): Int{
		var i = ii
		var sum : Int = 0
		while(i > 0){
			sum += bits[i]
			i -= (i and -i)
		}
		return sum
	}

	val array = IntArray(1) + f.readLine().split(" ").map{it.toInt()}

	val answer = IntArray(q)

	val queries = Array<Triple<Int,Int,Int>>(q){j ->
		val (l,r) = f.readLine().split(" ").map{it.toInt()}
		Triple(l,r,j)
	}

	queries.sortBy{it.second}

	val lastvisit = IntArray(n+1){-1}
	var querycount = 0
	for(k in 1..n){
		if(lastvisit[array[k]] != -1){
			update(lastvisit[array[k]],-1)
		}

		lastvisit[array[k]] = k
		update(k,1)

		while(querycount < q && queries[querycount].second == k){
			//println("${psum(queries[querycount].second+1)} ${psum(queries[querycount].first)}")
			answer[queries[querycount].third] = psum(queries[querycount].second)-psum(queries[querycount].first-1)
			querycount++
		}
	}

	println(answer.joinToString("\n"))

}
