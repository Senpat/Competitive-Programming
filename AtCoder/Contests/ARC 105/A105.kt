import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (a,b,c,d) = f.readLine().split(" ").map{it.toInt()}
	val sum = a+b+c+d
	if(sum%2 == 0){
		if(a == sum/2 || b == sum/2 || c == sum/2 || d == sum/2){
			println("Yes")
			return
		}
		if(a+b == sum/2 || a+c == sum/2 || a+d == sum/2){
			println("Yes")
			return
		}
	}

	println("No")


}
