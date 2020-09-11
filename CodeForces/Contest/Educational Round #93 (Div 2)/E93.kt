import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val firets = TreeSet<Long>()
	val lightts = TreeSet<Long>()

	var sumall = 0L
	var sumlight = 0L

	val higher = TreeSet<Long>()					//stores highest (lightts.size) elements
	var sumhigher = 0L								//sum of elements in higher

	val lower = TreeSet<Long>()					//all elements not in higher

	for(q in 0 until n){
		val (tp,dq) = f.readLine().split(" ").map{it.toLong()}

		if(dq < 0L){
			val d = dq*-1L
			if(tp == 0L){
				//remove fire
				sumall-=d
				firets.remove(d)
				if(higher.contains(d)){
					sumhigher-=d
					higher.remove(d)
					val lowerlast = lower.pollLast()
					sumhigher+=lowerlast
					higher.add(lowerlast)
				} else {
					lower.remove(d)
				}
			} else {
				//remove lightning
				sumall-=d
				sumlight-=d
				lightts.remove(d)
				if(higher.contains(d)){
					sumhigher-=d
					higher.remove(d)
				} else {
					lower.remove(d)
					val hf = higher.pollFirst()
					sumhigher -= hf
					lower.add(hf)
				}
			}

		} else {
			val d = dq
			if(tp == 0L){
				//add fire
				firets.add(d)
				sumall += d
				if(higher.size > 0){
					val hf = higher.first()
					if(d > hf){
						higher.add(d)
						sumhigher += d
						lower.add(hf)
						sumhigher -= hf
						higher.remove(hf)
					} else {
						lower.add(d)
					}
				} else {
					lower.add(d)
				}
			} else {
				//add lightning
				lightts.add(d)
				sumall += d
				sumlight += d
				if(lower.size > 0){
					val lowerlast = lower.last()
					if(lowerlast > d){
						higher.add(lowerlast)
						sumhigher += lowerlast
						lower.remove(lowerlast)
						lower.add(d)
					} else {
						higher.add(d)
						sumhigher += d
					}
				} else {
					higher.add(d)
					sumhigher += d
				}
			}

		}

		var answer = 0L
		if(lightts.size == 0 && firets.size == 0){
			answer = 0L
		} else if(lightts.size == 0){
			answer = sumall
		} else if(firets.size == 0){
			answer = sumall*2 - lightts.first()
		} else if(lightts.first() > firets.last()){
			answer = sumall + sumlight - lightts.first() + firets.last()
		} else {
			answer = sumall + sumhigher
		}

		println(answer)

	}
}
