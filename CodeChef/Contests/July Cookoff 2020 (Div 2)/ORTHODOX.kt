import java.io.*
import java.util.*
import kotlin.math.*

fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}

		//check for 0s
		fun check0() : Boolean{
			for(k in 0 until n) if(array[k] == 0L) return true
			return false
		}

		if(n == 1){
			println("YES")
			continue
		} else if(check0()){
			println("NO")
			continue
		}

		//all numbers > 0 now

		val begin = Array(n){BooleanArray(62){false}}
		val end = Array(n){BooleanArray(62){false}}

		//fill begin
		//fill begin[0]
		for(i in 0 until 62){
			if((array[0] and (1L shl i)) == (1L shl i)) begin[0][i] = true
		}

		for(k in 1 until n){
			for(i in 0 until 62){
				if(begin[k-1][i] || ((array[k] and (1L shl i)) == (1L shl i))) begin[k][i] = true
			}
		}

		//fill end
		for(i in 0 until 62){
			if((array[n-1] and (1L shl i)) == (1L shl i)) end[n-1][i] = true
		}

		for(k in n-2 downTo 0){
			for(i in 0 until 62){
				if(end[k+1][i] || ((array[k] and (1L shl i)) == (1L shl i))) end[k][i] = true
			}
		}

		fun check() : Boolean{
			for(k in 0 until n){
				if(k > 0){
					var fail = true
					for(i in 0 until 62){
						if(((array[k] and (1L shl i)) == (1L shl i)) && !begin[k-1][i]){
							fail = false
							break
						}
					}
					if(fail) return false
				}
				if(k < n-1){
					var fail = true
					for(i in 0 until 62){
						if(((array[k] and (1L shl i)) == (1L shl i)) && !end[k+1][i]){
							fail = false
							break
						}
					}
					if(fail) return false
				}
			}
			return true
		}

		if(check()){
			println("YES")
		} else {
			println("NO")
		}


	}
}
