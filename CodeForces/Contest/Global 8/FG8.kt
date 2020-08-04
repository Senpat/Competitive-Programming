import java.io.*
import java.util.*
import kotlin.math.*
import kotlin.random.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	/*
	if(n==3){
		println("0")
		return
	} else if(n==4 || n==5){
		println("2 1 3")
		val nothing = f.readLine().toInt()
		println("0")
		return
	}*/

	val lamps = BooleanArray(n+1)

	val q : Queue<Int> = LinkedList<Int>()
	for(k in 1 until n step 2){
		q.add(k)
	}

	while(q.size > 1){
		//pop 2
		val a = q.poll()
		val b = q.poll()

		lamps[a] = true
		lamps[b] = true

		println("2 $a $b")
		System.out.flush()

		val v = f.readLine().toInt()


		if(v == n){
			q.add(1)
			lamps[1] = false
		} else if(v % 2 == 1){
			q.add(v)
			lamps[v] = false
		} else {
			q.add(v+1)
			lamps[v+1] = false
		}
	}

	val query = StringBuilder()
	var num = 0
	for(k in 1..n){
		if(!lamps[k]){
			num++
			query.append(" $k")
		}
	}




	println(0)


}
