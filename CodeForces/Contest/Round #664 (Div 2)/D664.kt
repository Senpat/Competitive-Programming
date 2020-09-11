import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,d,mi) = f.readLine().split(" ").map{it.toInt()}
	val m = mi.toLong()

	val array = f.readLine().split(" ").map{it.toLong()}

	val leq = mutableListOf<Long>()
	val gre = mutableListOf<Long>()

	for(k in 0 until n){
		if(array[k] <= m) leq.add(array[k])
		else gre.add(array[k])
	}

	leq.sort()
	gre.sort()

	val psumleq = LongArray(leq.size+1)
	val psumgre = LongArray(gre.size+1)

	psumleq[0] = 0L
	for(k in 1..leq.size){
		psumleq[k] = psumleq[k-1]+leq[k-1]
	}
	psumgre[0] = 0L
	for(k in 1..gre.size){
		psumgre[k] = psumgre[k-1]+gre[k-1]
	}

	var answer = 0L

	for(k in 0..leq.size){
		//how many lowers that you take

		//get sum of top k lowers
		val lowers = psumleq[leq.size] - psumleq[leq.size-k]

		//get number of greaters
		val numgre = (n-k+d)/(d+1)

		val greaters = psumgre[gre.size] - psumgre[max(0,gre.size-numgre)]
		//println("$k $lowers $numgre $greaters")
		answer = max(answer,lowers+greaters)
	}

	println(answer)
}
