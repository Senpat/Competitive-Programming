import java.io.*
import java.util.*
import kotlin.math.*
//finds lcs, WRONG
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	val a1 = f.readLine().map{Character.getNumericValue(it)}.toIntArray()
	val b1 = f.readLine().map{Character.getNumericValue(it)}.toIntArray()

	val a = mutableListOf<Int>()
	val b = mutableListOf<Int>()
	var counter = 0

	for(k in 0 until n){
		if(a1[k] != b1[k]){
			a.add(a1[k])
			b.add(b1[k])
			counter += a1[k]*2-1
		}
	}

	if(counter != 0){
		println(-1)
		return
	}

	if(a.size == 0){
		println(0)
		return
	}

	val stk10 = Stack<Int>()
	val stk01 = Stack<Int>()

	var max10 = 0
	var max01 = 0

	for(k in 0 until a.size){
		if(a[k] == 1){
			//1 0
			if(stk01.size > 0){
				stk01.pop()
			} else {
				stk10.add(1)
				max10 = max(max10,stk10.size)
			}
		} else {
			if(stk10.size > 0){
				stk10.pop()
			} else {
				stk01.add(1)
				max01 = max(max01,stk01.size)
			}
		}
	}

	println(max10+max01)

}
