import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{abs(it.toInt())}

		val sj = StringJoiner(" ")
		for(k in 0 until n){
			if(k%2 == 0) sj.add("${array[k]}")
			else sj.add("${array[k]*-1}")
		}

		println(sj)
	}
}
