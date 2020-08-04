import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (a,v) = f.readLine().split(" ").map{it.toLong()}
	val (b,w) = f.readLine().split(" ").map{it.toLong()}
	val t = f.readLine().toLong()

	if(a == b){
		println("YES")
	} else if(w >= v){
		println("NO")
	} else {
		val gain : Long = t*v - t*w
		if(abs(a-b) <= gain){
			println("YES")
		} else {
			println("NO")
		}
	}
}
