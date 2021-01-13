import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val a = f.readLine().split(" ").map{it.toLong()}
		val b = f.readLine().split(" ").map{it.toLong()}

		fun calc(x : Int) : Long{
			//x is shift
			var min = 1000005L
			for(k in 0 until n){
				min = min(min,abs(a[k]-b[(k+x+n)%n]))
			}

			return min
		}

		var maxc = 0L
		var maxshift = -1

		for(k in 0 until n){
			val i = calc(k)
			if(i >= maxc){
				maxc = i
				maxshift = k
			}
		}

		val sb = StringBuilder("")
		for(k in 0 until n){
			sb.append("${(k+maxshift+n)%n+1} ")
		}

		println(sb)

	}
}
