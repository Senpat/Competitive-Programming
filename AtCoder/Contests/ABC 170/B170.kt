import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val max = n*4
	val min = n*2

	if(m%2 == 0 && m >= min && m <= max){
		println("Yes")
	} else {
		println("No")
	}
}
