import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val s = f.readLine().toCharArray()
		val n = s.size

		var rn = 0
		var pn = 0
		var sn = 0
		for(k in 0 until n){
			if(s[k] == 'R') rn++
			if(s[k] == 'P') pn++
			if(s[k] == 'S') sn++
		}

		if(rn >= pn && rn >= sn){
			println("P".repeat(n))
		}
		else if(pn >= rn && pn >= sn){
			println("S".repeat(n))
		}
		else if(sn >= rn && sn >= pn){
			println("R".repeat(n))
		}

	}
}
