import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun checkprime(x : Long) : Boolean{
		var i = 2L
		while(i*i <= x){
			if(x%i == 0L) return false
			i++
		}
		return true
	}

	fun getnextprime(x : Long) : Long{
		var i = x
		while(!checkprime(i)) i++
		return i
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toLong()

		val a = getnextprime(n+1L)
		val b = getnextprime(a+n)

		println(a*b)
	}
}
