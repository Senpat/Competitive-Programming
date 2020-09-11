import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		if(n<m){
			println(m-n)
		} else {
			if(n%2 != m%2)println(1)
			else println(0)
		}
	}
}
