import java.io.*
import java.util.*
import kotlin.math.*

fun main(天神偶么卡儿 : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val a = f.readLine().split(" ").map{it.toInt()}
		val b = f.readLine().split(" ").map{it.toInt()}

		val freqa = HashMap<Int,Int>()
		val freqb = HashMap<Int,Int>()

		var min = Int.MAX_VALUE

		for(k in 0 until n){
			freqa[a[k]] = (freqa[a[k]] ?: 0) + 1
			freqb[b[k]] = (freqb[b[k]] ?: 0) + 1
			min = min(min,min(a[k],b[k]))
		}

		fun check(fa : HashMap<Int,Int>, fb : HashMap<Int,Int>) : Boolean{
			for(i in fa.keys){
				if(fa[i]!! + (fb[i] ?: 0) % 2 == 1) return false
			}

			for(i in fb.keys){
				if(fb[i]!! + (fa[i] ?: 0) % 2 == 1) return false
			}
			return true
		}

		if(!check(freqa,freqb)){
			println("-1")
			continue
		}

		val pqa = PriorityQueue<Int>()
		val pqb = PriorityQueue<Int>(compareBy({-it}))

		for(i in freqa.keys){
			if(freqa[i]!! > (freqb[i] ?: 0)){
				for(k in 0 until (freqa[i]!! - (freqb[i] ?: 0))/2){
					pqa.add(i)
				}
			}
		}

		for(i in freqb.keys){
			if(freqb[i]!! > (freqa[i] ?: 0)){
				for(k in 0 until (freqb[i]!! - (freqa[i] ?: 0))/2){
					pqb.add(i)
				}
			}
		}

		var answer = 0L

		while(!pqa.isEmpty()){
			answer += min(min*2,min(pqa.poll(),pqb.poll())).toLong()
		}

		println(answer)

	}
}
