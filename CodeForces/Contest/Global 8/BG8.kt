import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun pow (i : Long, x : Int) : Long{
		var ret = 1L
		for(k in 1..x) ret *= i
		return ret
	}

	val n = f.readLine().toLong()

	var i = 1L
	while(pow(i+1,10) < n){
		i++
	}

	var x = 0
	while(pow(i+1,x) * pow(i,10-x) < n){
		x++
	}

	val s = "codeforces"
	val sb = StringBuilder()
	for(k in 0 until 10){
		if(k < x){
			for(j in 0 until i+1){
				sb.append(s[k.toInt()])
			}
		} else {
			for(j in 0 until i){
				sb.append(s[k.toInt()])
			}
		}
	}

	println(sb)
}
