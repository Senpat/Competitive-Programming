import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (a,b,c) = f.readLine().split(" ").map{it.toLong()}

		var answer1 = -1L
		var answer2 = -1L
		if(a < c){
			answer1 = 1L
		}

		if(a*b > c){
			answer2 = b
		}

		println("$answer1 $answer2")
	}
}
