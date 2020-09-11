import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.toIntArray()
		val sorted = IntArray(n){array[it]}.sorted()

		fun check() : Boolean{
			//get min
			var minv = Int.MAX_VALUE
			for(i in array) minv = min(minv,i)

			for(k in 0 until n){
				if(array[k] != sorted[k] && array[k]%minv != 0) return false
			}

			return true
		}

		if(check()){
			println("YES")
		} else {
			println("NO")
		}
	}
}
