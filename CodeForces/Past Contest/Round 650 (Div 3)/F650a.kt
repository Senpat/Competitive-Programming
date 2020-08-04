import java.io.*
import java.util.*
import kotlin.math.*
//solves F1 version (not done)
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}
		val sorted = IntArray(n){j -> array[j]}.sorted()

		val hmap = HashMap<Int,Int>()

		for(k in 0 until n){
			hmap.put(array[k],k)
		}

		var answer = 0
		for(k in 0 until n){
			var i = k
			var len = 1
			while(i+1 < n && hmap[sorted[i+1]]!! > hmap[sorted[i]]!!){
				len++
				i++
			}
			answer = max(answer,len)

		}

		println(n-answer)
	}
}
