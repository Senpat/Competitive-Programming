//Glad to see you!
import java.io.*
import java.util.*
import kotlin.math.*
//semi-t
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val (n,m) = f.readLine().split(" ").map{it.toInt()}

	fun find(ll : Int, rr : Int) : Int{
		if(ll > rr) return -1
		if(ll == rr){
			if(ll == n){
				println("1 $n ${n-1}")
				System.out.flush()

				val s = f.readLine()
				if(s.equals("TAK")){
					return ll
				} else {
					return -1
				}
			} else {
				println("1 $ll ${ll+1}")
				System.out.flush()

				val s = f.readLine()
				if(s.equals("TAK")){
					return ll
				} else {
					return -1
				}
			}
		}


		var l = ll
		var r = rr
		var ans = -1
		while(l < r){
			val mid = l + (r-l)/2

			println("1 ${mid} ${mid+1}")
			System.out.flush()

			val s = f.readLine()

			if(s.equals("TAK")){
				//left
				ans = mid
				r = mid
			} else {
				ans = mid+1
				l = mid+1
			}
		}

		if(ans == ll && ans > 1){
			println("1 ${ans} ${ans-1}")
			System.out.flush()

			val s = f.readLine()

			if(s.equals("NIE")){
				return -1
			}
		} else if(ans == rr && ans < n){
			println("1 $ans ${ans+1}")
			System.out.flush()

			val s = f.readLine()

			if(s.equals("NIE")){
				return -1
			}
		}

		return ans
	}


	val a1 = find(1,n)

	val a2a = find(1,a1-1)
	val a2b = find(a1+1,n)

	if(a2a == -1) println("2 $a1 $a2b")
	else println("2 $a1 $a2a")


}
