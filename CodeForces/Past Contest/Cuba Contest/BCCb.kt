import java.io.*
import java.util.*
import kotlin.math.*
//solves B2
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,a,b) = f.readLine().split(" ").map{it.toInt()}

		if(n >= a + b){
			println("Yes")

			val answera = Array(n){IntArray(a){0}}
			val answerb = Array(n){IntArray(b){0}}

			for(k in 0 until n){
				for(j in 0 until a){
					answera[k][j] = (k+j+n)%n+1
				}
			}

			for(k in 0 until n){
				for(j in 0 until b){
					answerb[k][j] = (k+j+1+n)%n+1
				}
			}


			for(k in 0 until n){
				println(answera[k].joinToString(" "))
			}
			for(k in 0 until n){
				println(answerb[k].joinToString(" "))
			}

		} else println("No")
	}
}
