import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}
		val sj = StringBuilder()
		for(k in 0 until n){
			if(k%3 == 0) sj.append("a")
			else if(k%3 == 1) sj.append("b")
			else sj.append("c")
		}

		println(sj)
	}
}
