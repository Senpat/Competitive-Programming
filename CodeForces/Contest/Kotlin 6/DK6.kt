import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toLong()}

		fun check(x : Long) : Boolean{
			var times = 1L
			var total = m-x
			var cur = x
			while(cur > 1L && times < n){
				 cur = (cur+1L)/2L
				 total-=cur
				 times++
			}

			total -= max(0,n-times)
			return total >= 0

		}

		//binary search on last number
		var l = 1L
		var r = m
		var ans = -1L

		while(l <= r){
			val mid = l + (r-l)/2

			if(check(mid)){
				l = mid+1
				ans = mid
			} else {
				//try smaller
				r = mid-1
			}

		}

		println(ans)


	}
}
