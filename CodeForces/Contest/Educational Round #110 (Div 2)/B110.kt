import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun gcd(a : Int, b : Int) : Int{
		if(a < b){
			if(a == 0) return b
			return gcd(b%a,a)
		} else if(a > b){
			if(b == 0) return a
			return gcd(a%b,b)
		}
		return a
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		var answer = 0
		for(k in 0 until n){
			for(j in (k+1) until n){
				if(array[k]%2 == 0 || array[j]%2 == 0){
					answer++
					continue
				}
				if(gcd(array[k],array[j]) > 1) answer++
			}
		}

		println(answer)
	}
}
