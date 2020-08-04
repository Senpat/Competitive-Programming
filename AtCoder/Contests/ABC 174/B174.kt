import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,di) = f.readLine().split(" ").map{it.toInt()}
	val d = di.toLong()
	val d2 = d*d

	var answer = 0
	for(k in 0 until n){
		val (a,b) = f.readLine().split(" ").map{it.toLong()}
		if(a*a+b*b <= d2) answer++
	}
	println(answer)
}
