import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toLong()
	var answer = 0L

	for(k in 1L until n){
		answer += (n-1)/k
	}

	println(answer)
}
