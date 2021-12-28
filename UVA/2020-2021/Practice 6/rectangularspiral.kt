import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (useless,x,y) = f.readLine().split(" ").map{it.toInt()}
		if(y > x){
			println("$q 2 $x $y")
		} else if(y >= 4){
			println("$q 6 1 2 3 ${x+5-y} ${x+2} ${x+3}")
		} else {
			println("$q NO PATH")
		}
	}
}
