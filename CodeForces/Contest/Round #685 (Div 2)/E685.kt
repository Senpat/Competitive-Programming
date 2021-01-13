import java.io.*
import java.util.*
import kotlin.math.*
//hopefully solves E2 and E1
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val xors = IntArray(n){0}

	for(k in 0 until n-1){
		println("XOR ${k+1} ${k+2}")
		System.out.flush()

		xors[k] = readLine()!!.toInt()
	}

	println("XOR $n 1")
	xors[n-1] = readLine()!!.toInt()

	println("AND $n 1")
	val ander = readLine()!!.toInt()

	val tries = IntArray(n){0}
	var found = false
	for(k in 0 until n){
		tries[0] = k
		for(j in 1 until n){
			tries[j] = tries[j-1] xor xors[j-1]
		}
		if((tries[n-1] xor tries[0]) == xors[n-1] && (tries[n-1] and tries[0]) == ander){
			println("! " + tries.joinToString(" "))
			found = true
			break
		}

	}

	if(!found) println("! fail")

}
