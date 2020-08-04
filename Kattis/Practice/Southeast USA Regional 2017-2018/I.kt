import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val answer = PriorityQueue<Pair<Int,Int>>(compareBy({it.first},{it.second}))

	for(k in 1 until n){
		if(k > 1 && n%k == 0) answer.add(Pair(k,k))
		if(n%(2*k+1) == 0 || ((n >= 3*k+2) && (n-(k+1))%(2*k+1) == 0)) answer.add(Pair(k+1,k))
	}

	while(!answer.isEmpty()){
		val p = answer.poll()
		println("${p.first} ${p.second}")
	}
}
