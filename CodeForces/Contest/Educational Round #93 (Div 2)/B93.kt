import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()
		val n = array.size

		//get blocks of 1
		val pq = PriorityQueue<Int>(compareBy{-it})

		var curblocksize = 0
		for(k in 0 until n){
			if(array[k] == 0){
				if(curblocksize > 0) pq.add(curblocksize)
				curblocksize = 0
			} else {
				curblocksize++
			}
		}

		if(curblocksize > 0) pq.add(curblocksize)

		var answer = 0
		while(!pq.isEmpty()){
			answer += pq.poll()
			if(!pq.isEmpty()) pq.poll()
		}

		println(answer)
	}
}
