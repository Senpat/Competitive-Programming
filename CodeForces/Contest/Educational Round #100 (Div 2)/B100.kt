import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}

		var odd = 0L
		var even = 0L

		for(k in 0 until n){
			if(k%2 == 1) odd += array[k]
			else even += array[k]
		}

		val sj = StringJoiner(" ")
		for(k in 0 until n){
			if((even >= odd && k%2 == 0) || (even < odd && k%2 == 1)) sj.add("${array[k]}")
			else sj.add("1")
		}

		println(sj.toString())
	}
}
