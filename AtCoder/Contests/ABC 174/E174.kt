import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,mi) = f.readLine().split(" ").map{it.toInt()}
	val m = mi.toLong()

	val array = f.readLine().split(" ").map{it.toLong()}

	fun check(x : Long) : Boolean{
		var count = 0L
		for(k in 0 until n){
			count += (array[k] + x-1)/x-1
		}
		return count <= m
	}

	var l = 1L
	var r = 1000000000L
	var ans = 1000000000L
	while(l <= r){
		val mid = l + (r-l)/2
		if(check(mid)){
			ans = mid
			r = mid-1
		} else {
			l = mid+1
		}
	}

	println(ans)
}
