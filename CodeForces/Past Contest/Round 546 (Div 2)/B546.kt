import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	var answer = 2*n+1
	answer += min(n+m-2,2*n-m-1)

	println(answer)
}
