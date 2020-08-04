import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toLong()}
		val (l1,r1) = f.readLine().split(" ").map{it.toLong()}
		val (l2,r2) = f.readLine().split(" ").map{it.toLong()}

		val minl = min(l1,l2)
		val maxl = max(l1,l2)
		val minr = min(r1,r2)
		val maxr = max(r1,r2)

		val curi = n*max(0L,min(r1,r2)-max(l1,l2))
		if(curi >= m){
			println(0)
			continue
		}
		var answer = -1L
		if(curi == 0L && min(r1,r2)!=max(l1,l2)){
			//no overlap

			val distper = maxl-minr
			val onesper = (maxr-minl)
			//println("d o $distper $onesper")
			if(distper*2L >= onesper){
				//do one
				if(onesper >= m){
					answer = distper + m
				} else {
					answer = distper + onesper + 2L*(m-onesper)
				}
			} else {

				//loops needed
				val needed = min(n,(m+onesper-1L)/onesper)

				//do needed-1 times, double the rest
				var answer1 = -1L
				var answer2 = -1L

				if(needed > 1L){
					answer1 = (needed-1)*(distper+onesper) + 2L*(m-((needed-1L)*(onesper)))
				} else {
					answer1 = Long.MAX_VALUE
				}

				//do needed times
				if(onesper*n >= m){
					answer2 = (m+onesper-1L)/onesper*distper + m
				} else {
					answer2 = n*(onesper+distper) + 2L*(m-onesper*n)
				}
				//println("$answer1 $answer2")
				answer = min(answer1,answer2)
			}
		} else {
			//overlap

			val ones = n*((maxr-minr) + (maxl-minl))

			if(curi + ones >= m){
				answer = m-curi
			} else {
				answer = ones + 2L*(m-curi-ones)
			}

		}

		println(answer)
	}
}
