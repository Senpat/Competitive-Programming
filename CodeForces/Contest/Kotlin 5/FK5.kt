import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m,s) = f.readLine().split(" ").map{it.toInt()}
	val array = f.readLine().split(" ").map{it.toLong()}

	fun check(x : Long) : Boolean{
		var rem = m

		var pq = PriorityQueue<Long>(compareBy{it*-1L})
		var pqsum = 0L

		var i = 0
		while(i < n){
			if(pq.size < s){
				pq.add(array[i])
				pqsum += array[i]
			}
			if(pq.size == s){
				if(pqsum <= x){
					pq.clear()
					pqsum = 0L
				} else {
					if(rem == 0) return false
					rem--
					pqsum -= pq.poll()
				}
			}
			i++
		}

		while(pqsum > x){
			if(rem == 0) return false
			rem--
			pqsum -= pq.poll()
		}

		return true;
	}

	var l = 0L
	var r = 10000000001L
	var ans = -1L

	while(l <= r){
		val mid = l + (r-l)/2
		//println("$mid ${check(mid)}")
		if(check(mid)){
			r = mid-1
			ans = mid
		} else {
			l = mid+1
		}
	}

	println(ans)
}
