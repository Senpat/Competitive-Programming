import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}.sorted()

		fun check() : Boolean{
			for(k in 1 until n){
				if(array[k] > array[k-1]+1) return false
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
