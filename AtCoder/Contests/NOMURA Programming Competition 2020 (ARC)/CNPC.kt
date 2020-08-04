import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toLong()}

	var answer : Long = 1L

	val pq = PriorityQueue<Pair<Long,Long>>(compareBy({it.first}))
	if(array[0] > 1L){
		println(-1)
		return
	} else if(array[0] == 0L){
		pq.add(Pair(0L,0L))
		pq.add(Pair(0L,0L))
	}
	for(k in 1..n){
		if(array[k] == 0L) continue
		for(j in 1..array[k]){
			if(pq.isEmpty() || pq.peek().first >= k.toLong()){
				println(-1)
				return
			}

			val p = pq.poll()
			answer += k-p.first
			//println("${p.first} answer")
			if(p.second > p.first) pq.add(Pair(p.first+1,p.second))
			if(p.first+1 <= (k-1).toLong()) pq.add(Pair(p.first+1,(k-1).toLong()))
		}
	}

	println(answer)
}
