import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		fun calc(s : Int) : Int{
			//make frequency table
			val freq = IntArray(51)
			for(k in 0 until n) freq[array[k]]++

			var ret = 0
			for(k in 0 until (s+1)/2){
				if(s-k > 50) continue
				ret += min(freq[k],freq[s-k])
			}

			if(s%2 == 0){
				ret += freq[s/2]/2
			}

			return ret
		}

		var answer = 0
		for(k in 2..100){
			answer = max(answer,calc(k))
		}

		println(answer)
	}
}
