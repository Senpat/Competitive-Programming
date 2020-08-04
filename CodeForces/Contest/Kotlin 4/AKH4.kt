import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		//find n1
		val n1 = n/(1+m+m*m+m*m*m)
		val n2 = m*n1
		val n3 = m*n2
		val n4 = m*n3

		println("$n1 $n2 $n3 $n4")
	}
}
