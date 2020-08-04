import java.io.*
import java.util.*
import kotlin.math.*
//got help from someone's submission
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()
	var array = f.readLine().split(" ").map{it.toInt()}

	var max = 0
	val stk = Stack<Int>()

	stk.add(0)
	val answer = IntArray(n){-1}

	for(k in 1 until n){

		answer[k] = 1
		while(!stk.isEmpty() && array[k] > array[stk.peek()]){
			val p = stk.pop()
			if(answer[p] == -1){
				answer[k] = -1
				break
			}
			answer[k] = max(answer[k],answer[p]+1)
		}
		max = max(max,answer[k])
		stk.add(k)
	}

	println(max)

}
