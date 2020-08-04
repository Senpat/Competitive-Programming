import java.io.*
import java.util.*
import kotlin.math.*

fun main(roger : Array<String>){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for (q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().toCharArray()

		val freq = IntArray(26)
		for(k in 0 until n){
			freq[array[k]-'a']++
		}

		fun check() : Boolean{
			for(k in 0 until 26){
				if(freq[k] % 2 == 1) return false
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
