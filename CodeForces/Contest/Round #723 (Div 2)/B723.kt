import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toLong()

		if(n >= 1100L){
			println("YES")
			continue
		}

		if(n%11L <= n/111L){
			println("YES")
		} else {
			println("NO")
		}

	}
}
