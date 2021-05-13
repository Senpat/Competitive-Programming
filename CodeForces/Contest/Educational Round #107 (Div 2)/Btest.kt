import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val pow10 = LongArray(12){0L}
	pow10[0] = 1L
	for(k in 1 until 12){
		pow10[k] = pow10[k-1]*10L
	}

	fun gcd(x : Long, y : Long) : Long{
		if(x <= y){
			if(x == 0L) return y
			return gcd(y%x,x)
		} else {
			if(y == 0L) return x
			return gcd(x%y,y)
		}
	}

	fun numdigits(xx : Long) : Int{
		var count = 0
		var x = xx
		while(x > 0){
			count++
			x/=10
		}

		return count
	}

	for(a in 1..9){
		for(b in 1..9){
			for(c in 1..min(a,b)){
				val a1 = pow10[a-1]
				val a2 = pow10[b-1] + pow10[c-1]

				if(numdigits(gcd(a1,a2)) != c){
					println("FAIL: $a $b $c")
				}

			}
		}
	}
}
