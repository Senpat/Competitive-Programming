import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toInt()}

		val answer = Array(n+1){CharArray(100){'a'}}

		for(k in 0 until n){
			//set answer[k+1] to answer[k]
			for(j in 0 until 100){
				answer[k+1][j] = answer[k][j]
			}

			//flip answer[k+1][array[k]]
			if(answer[k+1][array[k]] == 'a') answer[k+1][array[k]] = 'b'
			else answer[k+1][array[k]] = 'a'
		}

		for(k in 0..n){
			println(answer[k])
		}
	}
}
