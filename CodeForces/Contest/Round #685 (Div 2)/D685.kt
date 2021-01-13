import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (d,m) = f.readLine().split(" ").map{it.toLong()}

		var x = 0L
		//find biggest x such that 2(mx)^2 <= d^2
		while(2L*m*m*x*x <= d*d){
			x++
		}
		x--

		if((m*x+m)*(m*x+m) + m*m*x*x > d*d) println("Utkarsh")
		else println("Ashish")
	}
}
