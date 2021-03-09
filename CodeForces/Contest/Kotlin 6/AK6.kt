import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		var (ax,y) = f.readLine().split(" ").map{it.toLong()}

		var x = ax*1000000000
		var answer = 0L
		while(x >= ax){
			if(x <= y){
				y -= x
				answer++
			} else {
				x/=10
			}
		}

		answer += y

		println(answer)
	}
}
