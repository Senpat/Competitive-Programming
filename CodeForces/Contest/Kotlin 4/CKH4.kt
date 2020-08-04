import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m,x,y) = f.readLine().split(" ").map{it.toInt()}

		val array = f.readLine().split(" ").map{it.toLong()}
		var sorted = Array<Long>(n){0L}
		var sum : Long = 0L
		var max = 0L
		var overcount = 0
		for(k in 0 until n){
			sorted[k] = array[k]
			sum += array[k]
			max = max(max,array[k])
			if(array[k] > m){
				overcount++
			}
		}

		if(max <= m){
			println(0)
			continue
		}

	 	sorted.sortDescending()

		var answer1 = overcount*x
		var answer2 = 0

		var i = 0
		while(ceil(sum.toDouble()/n.toDouble()).toInt() > m){
			sum -= sorted[i]
			i++
			answer2 += x
		}

		answer2 += y

		println(min(answer1,answer2))





	}
}
