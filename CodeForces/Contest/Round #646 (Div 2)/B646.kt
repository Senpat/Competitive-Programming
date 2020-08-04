import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val array = f.readLine().toCharArray()
		val n = array.size

		val psum0 = IntArray(n+1)
		val psum1 = IntArray(n+1)

		psum0[0] = 0
		psum1[0] = 0

		for(k in 1..n){
			if(array[k-1] == '0'){
				psum0[k] = psum0[k-1]+1
				psum1[k] = psum1[k-1]
			} else {
				psum0[k] = psum0[k-1]
				psum1[k] = psum1[k-1]+1
			}
		}

		var answer = min(psum0[n],psum1[n])

		for(k in 0 until n){
			//0 from 0 to k, 1 from k+1 to n
			answer = min(answer, psum0[k] + psum1[n]-psum1[k])

			answer = min(answer, psum1[k] + psum0[n]-psum0[k])
		}

		println(answer)
	}
}
