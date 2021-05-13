import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()

		fun rev(ll : Int, rr : Int){
			var l = ll
			var r = rr
			while(l < r){
				var temp = array[l]
				array[l] = array[r]
				array[r] = temp
				l++
				r--
			}
		}

		var answer = 0
		for(k in 0 until n-1){
			val index = array.indexOf(k+1)
			answer += index-k+1
			rev(k,index)
		}

		println("Case #${q}: $answer")
	}
}
