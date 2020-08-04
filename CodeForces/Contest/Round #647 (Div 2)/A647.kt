import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toLong()}
		val a = min(n,m)
		val b = max(n,m)

		if(a==b){
			println(0)
			continue
		}

		if(b%a != 0L){
			println(-1)
			continue
		}

		var c = b/a
		var ctest = b/a

		while(ctest % 2 == 0L){
			ctest/=2
		}

		if(ctest != 1L){
			println(-1)
			continue
		}

		var answer = 0L
		while(c > 1L){
			answer++
			c/=8L
		}

		println(answer)
	}
}
