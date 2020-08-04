import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun checkpow2( x : Long) : Boolean{
		var i = x
		while(i % 2 == 0L){
			i /= 2
		}
		return i == 1L
	}

	fun is_prime(x : Long) : Boolean{
		if(x==2L) return true
		if(x%2 == 0L) return false
		var i = 3L
		while(i*i <= x){
			if(x % i == 0L) return false
			i += 2L
		}
		return true
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toLong()

		if(n == 1L){
			println("FastestFinger")
		} else if(n == 2L){
			println("Ashishgup")
		} else if(n%2 == 1L){
			println("Ashishgup")
		} else {
			if(is_prime(n/2L) || checkpow2(n)){
				println("FastestFinger")
			} else {
				println("Ashishgup")
			}
		}
	}
}
