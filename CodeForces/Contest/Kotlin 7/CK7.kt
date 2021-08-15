import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()

		var num1s = 0
		for(k in 0 until n){
			if(array[k] == 1) num1s++
		}

		var i = 0
		var answer = 0
		while(num1s > 0){
			//println(i)
			//eat at i
			answer += 1
			if(array[i] == 1) num1s--
			array[i] = -1

			if(num1s == 0) break

			//move i
			var move = 0
			while(move < m){
				i++
				if(i >= n) i = 0
				if(array[i] != -1){
					move++
				}
			}

		}

		println(answer)


	}
}
