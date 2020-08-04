import java.io.*
import java.util.*
import kotlin.math.*
//solves E1 version
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,pi) = f.readLine().split(" ").map{it.toInt()}
	val p : Long = pi.toLong()
	val array = f.readLine().split(" ").map{it.toLong()}.toLongArray().sorted()

	val answer = mutableListOf<Long>()

	outer@for(x in (array[n-1]-n+1)..max(n.toLong(),array[n-1])){
		//var prod : Long = 1L

		//number of numbers < x+k
		var i = 0

		while(i < n && array[i] <= x){
			i++
		}

		if(i.toLong() % p == 0L) continue
		//prod*=i

		for(k in 1 until n){
			while (i < n && array[i] <= x+k){
				i++
			}

			val mul = (i-k).toLong()
			if(mul % p == 0L) continue@outer
			//prod *= mul

		}

		answer.add(x)
	}

	println(answer.size)
	println(answer.joinToString(" "))
}
