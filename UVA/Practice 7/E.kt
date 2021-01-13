import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val array = Array(n){f.readLine()}

	//check increasing
	var incfail = false
	var decfail = false
	for(k in 1 until n){
		if(array[k] <= array[k-1]) incfail = true
		if(array[k] >= array[k-1]) decfail = true
	}

	if(!incfail) println("INCREASING")
	else if(!decfail) println("DECREASING")
	else println("NEITHER")
}
