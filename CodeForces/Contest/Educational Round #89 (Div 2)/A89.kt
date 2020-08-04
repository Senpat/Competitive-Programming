import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		var a = min(n,m)
		var b = max(n,m)

		var dif = b-a

		if(min(a,b/2) >= dif){
			var answer = dif
			a-=answer
			b-=answer*2
			if(min(a,b) >= 3){
				val add = (min(a,b)/3)*2
				answer += add
				a-=(add*3)/2
				b-=(add*3)/2
			}
			if(max(a,b) >= 2 && min(a,b) >= 1) answer++

			println(answer)
		} else {
			var answer = min(a,b/2)
			a-=answer
			b-=answer*2

			if(a >= 2 && b >= 1) answer++
			println(answer)
		}

	}
}
