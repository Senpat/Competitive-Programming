import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toInt()}

	val sorted = Array<Pair<Int,Int>>(n){j -> Pair(array[j],j)}.sorted()

	fun check(i : Int) : Boolean{
		//check before
	}

	var l = 0
	var r = n-1
	var ans = 0

	while(l <= r){
		val mid = l + (r-l)/2

		if(check(mid)){
			r = mid-1
			ans = mid
		} else {
			l = mid+1
		}
	}

	println(sorted[ans])

}
