import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val bits = LongArray(n+1)

	fun update(ii : Int, x : Long){
		var i = ii
		while(i <= n){
			bits[i] += x
			i += (i and -i)
		}
	}

	fun psum(ii : Int): Long{
		var i = ii
		var sum : Long = 0L
		while(i > 0){
			sum += bits[i]
			i -= (i and -i)
		}
		return sum
	}

}
