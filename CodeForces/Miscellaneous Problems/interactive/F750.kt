//New Year and Finding Roots
import java.io.*
import java.util.*
import kotlin.math.*
import kotlin.random.*
//small hint, uses 1 extra query.
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val random = Random(54321)



	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()

		var answer = -1
		//store queries
		val queries = HashMap<Int,List<Int>>()
		//val seen = BooleanArray((1 shl (n+1)) + 2){false}

		fun query(i : Int) : List<Int>{
			if(queries.containsKey(i)) return queries[i]!!
			//seen[i] = true
			println("? $i")
			System.out.flush()

			val size = f.readLine().toInt()
			queries[i] = f.readLine().split(" ").map{it.toInt()}.toMutableList().shuffled(random)
			return queries[i]!!
		}


		fun test(i : Int, pa : Int, d : Int) : Boolean{
			var cur = i
			var p = pa
			for(k in 0 until d-2){
				val neis = query(cur)
				if(neis.size == 1) return false
				if(neis.size == 2){
					answer = cur
					return true
				}
				//pick next
				for(nei in neis){
					if(nei == p) continue
					p = cur
					cur = nei
					break
				}
			}

			val last = query(cur)
			if(last.size == 2){
				answer = cur
				return true
			}
			return last.size == 1
		}

		//pick first node
		val first = random.nextInt(1,1 shl n)

		val initial = mutableListOf<Int>()
		initial.add(first)

		//query first
		val initialneis = query(first)

		if(initialneis.size == 2){
			println("! $first")
			System.out.flush()
			continue
		}


		if(initialneis.size == 1){
			var p = first
			var i = initialneis[0]

			while(true){
				initial.add(i)
				val cur = query(i)
				if(cur.size == 1){
					break
				}
				if(cur.size == 2){
					answer = i
					break
				}
				//get next
				for(nei in cur){
					if(nei == p) continue
					p = i
					i = nei
					break
				}
			}
		} else {
			//size is 3
			//do one path
			var p = first
			var i = initialneis[0]

			while(answer == -1){
				initial.add(0,i)
				val cur = query(i)
				if(cur.size == 1){
					break
				}
				if(cur.size == 2){
					answer = i
					break
				}
				//get next
				for(nei in cur){
					if(nei == p) continue
					p = i
					i = nei
					break
				}
			}

			p = first
			i = initialneis[1]

			while(answer == -1){
				initial.add(i)
				val cur = query(i)
				if(cur.size == 1){
					break
				}
				if(cur.size == 2){
					answer = i
					break
				}
				//get next
				for(nei in cur){
					if(nei == p) continue
					p = i
					i = nei
					break
				}
			}

		}

		if(answer != -1){
			println("! $answer")
			System.out.flush()
			continue
		}

		//get initial peak and initial depth
		//println(initial.joinToString(" "))
		val ipeak = initial[initial.size/2]
		val idepth = (initial.size+1)/2

		var next = -1
		for(posnext in queries[ipeak]!!){
			//if(posnext == initial[initial.size/2-1] || posnext == initial[initial.size/2+1]) continue
			if(queries.containsKey(posnext)) continue
			next = posnext
			break
		}

		var depth = idepth+1

		var parent = ipeak
		while(depth < n-1 && answer == -1){
			//go depth-1 times from next
			val nextnei = query(next)
			if(nextnei.size == 2){
				answer = next
				break
			}

			//should have size 3
			//pick one
			var try1 = -1
			var try2 = -1
			for(posnei in nextnei){
				if(posnei == parent) continue
				if(try1 == -1) try1 = posnei
				else try2 = posnei
			}
			//println("$try1 $try2")
			//see if try1 works
			if(!test(try1,next,depth)){
				parent = next
				next = try1
			} else {
				parent = next
				next = try2
			}


			depth++
		}
		if(answer != -1){
			println("! $answer")
			System.out.flush()
			continue
		}
		//on depth 6, have two unseen neighbors,

		val last = query(next)

		if(last.size == 2){
			println("! $next")
			System.out.flush()
			continue
		}


		var a1 = -1
		var a2 = -1
		for(lastnei in last){
			if(!queries.containsKey(lastnei)){
				if(a1 == -1) a1 = lastnei
				else a2 = lastnei
			}
		}

		if(a2 == -1) answer = a1
		else{
			if(query(a1).size == 2) answer = a1
			else answer = a2
		}

		println("! $answer")
		System.out.flush()
	}
}
