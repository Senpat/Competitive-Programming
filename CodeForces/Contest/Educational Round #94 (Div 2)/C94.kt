import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()
		val x = f.readLine().toInt()

		val n = array.size
		val answer = IntArray(n){1}

		for(k in 0 until n){
			if(array[k] == 0){
				if(k >= x) answer[k-x] = 0
				if(k+x < n) answer[k+x] = 0
			}
		}

		fun check() : Boolean{

			for(k in 0 until n){
				if(array[k] == 1){
					if((k >= x && answer[k-x] == 1) || (k+x < n && answer[k+x] == 1)) continue
					return false
				}
			}
			return true
		}


		if(check()){
			println(answer.joinToString(""))
		} else {
			println(-1)
		}
	}
}
