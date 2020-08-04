import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m1,m2) = f.readLine().split(" ").map{it.toInt()}
		val ex = f.readLine()

		val array = IntArray(n)
		if(ex[0] == '0') array[0] = 0
		else array[0] = m1

		for(k in 1 until n){
			if(ex[k] == '0') array[k] = 0
			 else {
				 array[k] = min(m2-array[k-1],m1)
			 }
		}

		var sum : Long= 0L
		for(k in 0 until n){
			sum += array[k].toLong()
		}

		println(sum)
	}
}
