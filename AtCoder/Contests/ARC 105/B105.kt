import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val array = f.readLine().split(" ").map{it.toInt()}

	fun gcd(a : Int, b : Int) : Int{
		if(a > b){
			if(b == 0) return a
			return gcd(a%b,b)
		} else if(a < b){
			if(a == 0) return b
			return gcd(b%a,a)
		} else return a
	}

	var gcd = array[0]
	for(k in 1 until n) gcd = gcd(gcd,array[k])

	println(gcd)
}
