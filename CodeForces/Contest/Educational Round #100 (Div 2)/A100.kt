import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (a,b,c) = f.readLine().split(" ").map{it.toInt()}

		if((a+b+c)%9 == 0 && min(a,min(b,c)) >= (a+b+c)/9){
			println("YES")
		} else {
			println("NO")
		}
	}
}
