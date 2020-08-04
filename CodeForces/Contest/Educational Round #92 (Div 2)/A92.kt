import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (l,r) = f.readLine().split(" ").map{it.toInt()}
		if(r < l*2){
			println("-1 -1")
		} else {
			println("$l ${2*l}")
		}
	}
}
