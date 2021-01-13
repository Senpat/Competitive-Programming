import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,a,b) = f.readLine().split(" ").map{it.toInt()}

		val answer = CharArray(n){'a'}
		for(k in 0 until n){
			if(k%a < b-1){
				answer[k] = 'a'+k%a
			} else {
				answer[k] = 'a'+b-1
			}
		}

		println(answer.joinToString(""))
	}
}
