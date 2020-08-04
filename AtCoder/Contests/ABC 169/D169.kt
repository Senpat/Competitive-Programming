import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	var n = f.readLine().toLong()

	val triangle = LongArray(46)
	triangle[0] = 0L
	for(i in 1..45){
		triangle[i] = triangle[i-1]+i.toLong()
	}



	var answer = 0L
	var i = 2L
	while(i*i <= n){
		var count = 0L
		while(n%i == 0L){
			n/=i
			count++
		}
		if(count == 0L){
			i++
			continue
		}
		var l = 1
		var r = 40
		var ans = -1
		while(l <= r){
			var mid = l + (r-l)/2
			//println("$i $mid")
			if(triangle[mid] <= count){
				ans = mid
				l = mid+1
			} else {
				r = mid-1
			}

		}
		//println("$i $ans")
		i++
		answer += ans.toLong()
	}

	if(n != 1L) answer++
	println(answer)
}
