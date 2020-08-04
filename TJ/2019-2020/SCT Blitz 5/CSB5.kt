import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,b,t) = f.readLine().split(" ").map{it.toInt()}

	val covered = BooleanArray(n){false}

	val st = StringTokenizer(f.readLine())
	for(k in 0 until b){
		covered[st.nextToken().toInt()-1] = true
	}

	fun check(x : Int) : Int{
		var i = 0
		var ans = 0
		while(i < n){
			if(covered[i]){
				i += x
				ans++
			} else {
				i++
			}
		}

		return ans
	}

	var l = 1
	var r = n
	var ans = -1

	while(l <= r){
		val mid = l + (r-l)/2

		if(check(mid) <= t){
			r = mid-1
			ans = mid
		} else {
			l = mid+1
		}
	}

	println(ans)

}
