import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (x,y,n,m) = f.readLine().split(" ").map{it.toLong()}

		var a = min(x,y)
		var b = max(x,y)
		if(a < m){
			println("No")
		} else {
			a-=m
			if(a+b >= n){
				println("Yes")
			} else {
				println("No")
			}
		}
	}
}
