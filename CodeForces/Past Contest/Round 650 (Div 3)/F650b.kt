import java.io.*
import java.util.*
import kotlin.math.*
//solves F2 version DOESN'T WORK
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		val sorted = (0 until n).sortedWith(compareBy({array[it]},{-it}))

		var answer = 0
		var i = 0
		while(i < n){
			//try a subseq starting from i
			var len = 1
			var maxi = 0
			while(i+1 < n && (sorted[i+1] >= maxi || array[sorted[i+1]] == array[sorted[i]])){
				len++
				maxi = max(maxi,sorted[i])
				i++
			}
			i++
			answer = max(answer,len)

		}


		println(n-answer)
	}
}
