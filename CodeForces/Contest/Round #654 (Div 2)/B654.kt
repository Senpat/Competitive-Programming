import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,r) = f.readLine().split(" ").map{it.toLong()}
		val num = min(n-1,r)

		var answer = num*(num+1L)/2L
		if(r >= n) answer++

		println(answer)
	}
}
