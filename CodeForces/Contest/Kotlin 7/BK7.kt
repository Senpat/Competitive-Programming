import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		fun check() : Boolean{
			for(k in 0 until n-1){
				if((array[k]%2) == (array[k+1]%2)) return false
			}
			return true
		}

		if(!check()){
			println("YES")
		} else {
			println("NO")
		}
	}
}
