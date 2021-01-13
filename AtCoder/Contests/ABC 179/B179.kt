import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = BooleanArray(n){
		val (a,b) = f.readLine().split(" ").map{it.toInt()}
		a==b
	}

	fun check() : Boolean{
		for(i in 0 until n-2){
			if(array[i] && array[i+1] && array[i+2]) return true
		}
		return false
	}

	if(check()){
		println("Yes")
	} else {
		println("No")
	}

}
