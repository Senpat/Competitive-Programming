import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray().map{Character.getNumericValue(it)}.toIntArray()
		//println(array.joinToString(" "))
		val n = array.size

		fun check0(x : Int) : Boolean{
			for(k in 0..(x-1)){
				if(array[k] == 1 && array[k+1] == 1) return false
			}
			return true
		}

		fun check1(x : Int) : Boolean{
			for(k in x+1 until n-1){
				//print(k)
				if(array[k] == 0 && array[k+1] == 0) return false
			}
			return true
		}

		fun check() : Boolean{

			for(k in 0 until n){
				if(k < n-1 && array[k] == 1 && array[k+1] == 0) continue
				if(check0(k) && check1(k)){
					//println(k)
					return true
				}
			}

			return false

		}

		if(check()){
			println("YES")
		} else {
			println("NO")
		}
	}
}
