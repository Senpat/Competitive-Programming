import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toLong()

		var answer = 0L

		var i = 1L

		while(i <= n){
			answer += n/i
			i*=2L
		}

		println(answer)
	}
}
