import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (a,b,c,d,e) = f.readLine().split(" ").map{it.toInt()}
	if(a == 0) println(1)
	if(b == 0) println(2)
	if(c == 0) println(3)
	if(d == 0) println(4)
	if(e == 0) println(5)
}
