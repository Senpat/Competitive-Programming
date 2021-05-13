import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,k1,k2) = f.readLine().split(" ").map{it.toInt()}
		val (w,b) = f.readLine().split(" ").map{it.toInt()}

		var wbool = w <= (min(k1,k2) + abs(k2-k1)/2)
		var bbool = b <= (min(n-k1,n-k2) + abs(k2-k1)/2)

		if(wbool && bbool){
			println("YES")
		} else {
			println("NO")
		}
	}
}
