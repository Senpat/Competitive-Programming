import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (a,b) = f.readLine().split(" ").map{it.toLong()}

		if(a == b){
			println("1")
			println("$b")
			continue
		}

		if(b % 2 == 1L){
			println("-1")
			continue
		}

		//find instance of 11
		var three = 3L
		var i = 0
		while(a and three != three && i < 40){
			three = three shl 1
			i++
		}

		if(i == 40){
			println("-1")
			continue
		}

		val answer = mutableListOf<Long>()
		answer.add(a)
		//2^63-2^40, lots of ones then lots of 0s
		var v1 = 9223370937343148032L
		v1 += 1L shl (i+1)
		answer.add(v1)

		//2^63-2^41
		var v2 = 9223369837831520256L
		answer.add(v2)

		//2^43-1-2^41
		var v3 = 6597069766655L
		answer.add(v3)

		answer.add(b)

		println(answer.size)
		println(answer.joinToString(" "))

	}
}
