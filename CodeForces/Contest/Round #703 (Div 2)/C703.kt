import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	fun query(l : Int, r : Int) : Int{
		if(l>=r) return -1
		println("? $l $r")
		System.out.flush()

		val ret = f.readLine().toInt()
		return ret
	}

	val whole = query(1,n)
	val left = if (whole == 1) -1 else query(1,whole)

	var l = -1
	var r = -1
	var ans = -1

	if(left == whole){
		//bs on the left
		l = 1
		r = whole
		ans = -1
		while(l <= r){
			val mid = l + (r-l)/2

			val x = query(mid,whole)
			if(x == whole){
				ans = mid
				l = mid+1
			} else {
				r = mid-1
			}
		}

	} else {
		//bs on the right
		l = whole
		r = n
		ans = -1
		while(l <= r){
			val mid = l + (r-l)/2

			val x = query(whole,mid)
			if(x == whole){
				ans = mid
				r = mid-1
			} else {
				l = mid+1
			}
		}
	}

	println("! $ans")
}
