import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val s = f.readLine().toCharArray()
		val n = s.size

		val neg = mutableListOf<Long>()

		var cur = 0
		for(k in 0 until n){
			if(s[k] == '+') cur++
			else cur--

			if(cur < 0){
				if(abs(cur) > neg.size){
					neg.add(k.toLong()+1L)
				}
			}
		}

		var answer = neg.sum() + n.toLong()
		println(answer)
	}
}
