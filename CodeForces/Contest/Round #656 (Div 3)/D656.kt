import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	fun Boolean.toInt() = if (this) 1 else 0

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().toCharArray()

		val chars = mutableListOf<Char>()

		var log = 0
		var i = n
		var cur = 'a'
		while(i > 0){
			chars.add(cur)
			cur++
			i/=2
			log++
		}

		if(n == 1){
			if(array[0] == 'a') println(0)
			else println(1)
			continue
		}

		var matrix = Array(log+2){IntArray(n)}



		//fill matrix[0] - best way to make last two per two
		for(k in 0 until n step 2){
			matrix[0][k] = min((array[k] != chars[chars.size-1]).toInt() + (array[k+1] != chars[chars.size-2]).toInt(),(array[k] != chars[chars.size-2]).toInt() + (array[k+1] != chars[chars.size-1]).toInt())
		}

		fun count (c : Char, l : Int, r : Int) : Int{
			//println("$c $l $r")
			var ret = 0
			for(k in l..r){
				if(array[k] == c) ret++
			}

			return r-l+1-ret
		}

		i = 4
		var index = 1
		while(i <= n){
			//get sizes of i
			for(k in 0 until n step i){
				matrix[index][k] = min(matrix[index-1][k] + count(chars[chars.size-index-2],k+i/2,k+i-1),matrix[index-1][k+i/2] + count(chars[chars.size-index-2],k,k+i/2-1))
			}
			index++
			i *= 2
		}

		println(matrix[index-1][0])
	}
}
