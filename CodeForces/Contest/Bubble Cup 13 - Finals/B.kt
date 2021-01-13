import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}
	val pairs = Array<Triple<Int,Int,Int>>(m){
		val(a,b,c) = f.readLine().split(" ").map{it.toInt()}
		Triple(a,b,c)
	}

	fun check(x : Int) : Boolean{
		val a = HashSet<Int>()
		val b = HashSet<Int>()

		for(k in 0 until m){
			if(pairs[k].third <= x){
				a.add(pairs[k].first)
				b.add(pairs[k].second)
			}
		}

		return a.size == n && b.size == n
	}

	var l = 1
	var r = 1000000000
	var ans = -1
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
