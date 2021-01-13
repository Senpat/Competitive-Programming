import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val array = f.readLine().split(" ").map{it.toInt()}

		if(array.sum() == m){
			println("YES")
		} else {
			println("NO")
		}
	}
}
