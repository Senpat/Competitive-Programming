import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,a,b) = f.readLine().split(" ").map{it.toInt()}

		if(n >= a + b) println("Yes")
		else println("No")
	}
}
