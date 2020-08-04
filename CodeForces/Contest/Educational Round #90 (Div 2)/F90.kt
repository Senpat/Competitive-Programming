import java.io.*
import java.util.*
import kotlin.math.*
//upsolve
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		val array = f.readLine().split(" ").map{it.toInt()}
		val nets = f.readLine().split(" ").map{it.toInt()}

		fun assess(x : Int) : Int{
			val subbed = IntArray(n)

			subbed[0] = x

			subbed[1] = min(array[1],nets[0]-x)
			for(k in 1 until n-1){
				val need = array[k] - subbed[k]			//the amount needed to finish kth place

				if(need > nets[k]) return -1

				subbed[k+1] = min(array[k+1],nets[k]-need)
			}

			val lastneed = array[n-1]-subbed[n-1]
			val firstneed = array[0]-subbed[0]

			if(nets[n-1] < lastneed) return -1
			if(nets[n-1] < lastneed+firstneed) return 1
			return 0

		}

		fun check() : Boolean{
			var l = 0
			var r = min(array[0],nets[0])
			while(l <= r){
				val mid = l + (r-l)/2

				val i = assess(mid)
				if(i == 0) return true

				if(i == -1){
					//fell short on one of them, allocate less to 0
					r = mid-1
				} else {
					l = mid+1
				}
			}


			return false

		}

		if(check()){
			println("YES")
		} else {
			println("NO")
		}

	}
}
