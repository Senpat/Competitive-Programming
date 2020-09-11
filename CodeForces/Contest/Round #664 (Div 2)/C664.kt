import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	val a = f.readLine().split(" ").map{it.toInt()}
	val b = f.readLine().split(" ").map{it.toInt()}

	fun check(x : Int) : Boolean{
		outer@for(k in 0 until n){
			//see if there is a number that a[k] can anded with so that it's bits and <= x
			for(j in 0 until m){
				val band = a[k] and b[j]
				if((x and band) == band) continue@outer
			}
			return false
		}
		return true
	}

	for(k in 0..512){
		if(check(k)){
			println(k)
			break
		}
	}
}
