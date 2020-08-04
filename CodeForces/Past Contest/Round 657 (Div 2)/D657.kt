import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,h,m,t) = f.readLine().split(" ").map{it.toInt()}

	val pq = PriorityQueue<Triple<Int,Int,Int>>(compareBy({it.first},{it.second}))
	val pq2 = PriorityQueue<Triple<Int,Int,Int>>(compareBy({it.first},{it.second}))

	for(k in 1..n){
		var (a,b) = f.readLine().split(" ").map{it.toInt()}

		//disregard hours

		b = b%(m/2)

		if(b+t >= m/2){
			if(b+t > m/2){
				pq.add(Triple(0,0,k))
				pq.add(Triple(t-(m/2-b),1,k))
				pq2.add(Triple(0,0,k))
				pq2.add(Triple(t-(m/2-b),1,k))
			}
			//start point
			pq.add(Triple(b+1,0,k))
			pq2.add(Triple(b+1,0,k))
		} else {
			pq.add(Triple(b+1,0,k))
			pq.add(Triple(b+t,1,k))
			pq2.add(Triple(b+1,0,k))
			pq2.add(Triple(b+t,1,k))
		}
	}

	var cur = 0
	while(!pq.isEmpty() && pq.peek().first == 0){
		cur++
		pq.poll()
	}

	var answer = cur



	while(!pq.isEmpty()){

	  	val p = pq.poll()
		if(p.second == 1) cur--
		else cur++

		answer = min(answer,cur)
		/*
		var prev = p.first

		while(!pq.isEmpty() && pq.peek().first == prev){
			val p1 = pq.poll()
			if(p1.second == 1) cur--
			else cur++

			answer = min(answer,cur)
		}*/
	}

	val answerset = HashSet<Int>()
	var answer2 = -1
	while(!pq2.isEmpty() && pq2.peek().first == 0){
		val p = pq2.poll()
		//println("${p.third}")
		answerset.add(p.third)
	}

	if(answerset.size == answer){
		answer2 = 0
	}

	while(!pq2.isEmpty() && answer2 == -1){

	  	val p = pq2.poll()
		if(p.second == 1){
			answerset.remove(p.third)
		} else {
			answerset.add(p.third)
		}

		if(answerset.size == answer){
			answer2 = p.first
		}
		/*
		var prev = p.first

		while(!pq2.isEmpty() && pq2.peek().first == prev){
			val p1 = pq2.poll()
			if(p1.second == 1) cur--
			else cur++

			answer = min(answer,cur)
		}*/
	}

	println("$answer $answer2")
	println(answerset.joinToString(" "))


}
