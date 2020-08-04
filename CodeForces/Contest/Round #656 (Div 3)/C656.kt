import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toInt()}

		fun check(x : Int) : Boolean{
			var s = x
			var e = n-1

			var prev = -1
			while(s <= e){
				if(array[s] < array[e]){
					if(prev > array[s]) return false
					prev = array[s]
					s++
				} else {
					if(prev > array[e]) return false
					prev = array[e]
					e--
				}
			}

			return true
		}

		var l = 0
		var r = n-1
		var ans = n-1

		while (l <= r){
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
}
