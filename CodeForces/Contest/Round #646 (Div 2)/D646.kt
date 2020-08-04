import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val sets = Array(m){HashSet<Int>()}
		for(k in 0 until m){
			val st = StringTokenizer(f.readLine())
			val curn = st.nextToken().toInt()
			for(j in 1..curn){
				sets[k].add(st.nextToken().toInt())
			}
		}


		//find biggest number
		val firstq = StringJoiner(" ")
		firstq.add("? $n")
		for(k in 1..n) firstq.add(k.toString())

		println(firstq)
		System.out.flush()

		val max = f.readLine().toInt()

		//binary search for biggest number
		var l = 1
		var r = n
		var ans = n

		while(l <= r){

			if(l == r){
				ans = l
				break
			}
			val mid = l + (r-l)/2
			//query from l to mid inclusive
			val query = StringJoiner(" ")
			query.add("? ${mid-l+1}")
			for(k in l..mid) query.add(k.toString())

			println(query)
			System.out.flush()

			val response = f.readLine().toInt()

			if(response == max){
				//ans = l
				r = mid
			} else {
				//ans = r
				l = mid+1
			}
			//println("$l $r $mid $ans")
		}
		//println(ans)
		//find which set has that index
		var maxindex = -1
		for(k in 0 until m){
			if(sets[k].contains(ans)){
				maxindex = k
				break
			}
		}

		var secondanswer = -1
		if(maxindex != -1){
			//query all numbers that are not in sets[k]
			val query = StringJoiner(" ")
			query.add("? ${n-sets[maxindex].size}")
			for(k in 1..n){
				if(!sets[maxindex].contains(k)) query.add(k.toString())
			}

			println(query)
			System.out.flush()

			secondanswer = f.readLine().toInt()
		}

		val answer = StringJoiner(" ")
		answer.add("!")

		for(k in 0 until m){
			if(k == maxindex){
				answer.add(secondanswer.toString())
			} else {
				answer.add(max.toString())
			}
		}
		println(answer)
		System.out.flush()

		val verdict = f.readLine()
		if(verdict.equals("Incorrect")) return

	}
}
