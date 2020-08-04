import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val edges = Array<Triple<Int,Int,Int>>(m){
			val (a,b,c) = f.readLine().split(" ").map{it.toInt()}
			Triple(a,b,c)
		}

		val maxes = IntArray(n+1)

		for(edge in edges){
			maxes[edge.first] = max(maxes[edge.first],edge.third)
			maxes[edge.second] = max(maxes[edge.second],edge.third)
		}

		//check possible
		fun check() : Boolean{
			for( edge in edges){
				if(min(maxes[edge.first],maxes[edge.second]) != edge.third) return false
			}
			return true
		}

		if(!check()){
			println("NO")
		} else {
			println("YES")
			println(maxes.joinToString(" ").substring(2))
		}


	}
}
