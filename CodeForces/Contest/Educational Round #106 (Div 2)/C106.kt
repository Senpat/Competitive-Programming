import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toLong()}

		var mineven = array[0]
		var minodd = array[1]


		var answer = (mineven+minodd)*n.toLong()
		var sumeven = array[0]
		var sumodd = array[1]

		var numodd = n.toLong()
		var numeven = n.toLong()

		for(k in 2 until n){
			if(k%2 == 0){
				mineven = min(mineven,array[k])
				sumeven += array[k]
				numeven--
			} else {
				minodd = min(minodd,array[k])
				sumodd += array[k]
				numodd--
			}

			answer = min(answer,sumeven-mineven + numeven*mineven + sumodd-minodd + numodd*minodd)

			//println(answer)

		}

		println(answer)

	}
}
