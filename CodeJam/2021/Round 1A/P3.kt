import java.io.*
import java.util.*
import kotlin.math.*
//first subtask
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val (n,m) = f.readLine().split(" ").map{it.toInt()}

		val array = Array<Pair<CharArray,Int>>>(n){
			val (s1,s2) = f.readLine().split(" ")
			Pair(s1.toCharArray(),s2.toInt())
		}


		fun check(x : Int) : Boolean{
			val bits = IntArray(m){0}
			for(k in 0 until m){
				if((x and (1 shl k)) == 0){
					bits[k] = 0
				} else {
					bits[k] = 1
				}
			}

			for(k in 0 until n){
				var count = 0
				for(j in 0 until m){
					if((bits[k] == 1 && array[k].first[j] == 'T') || (bits[k] == 0 && array[k].first[j] == 'F')){
						count++
					}
				}

				if(count != array[k].second) return false
			}

			return true
		}




		val works = mutableListOf<Int>()
		for(k in 0 until (1 shl m)){
			if(check(k)) works.add(k)
		}

		fun getsum(x : Int) : Int{
			val bits = IntArray(m){0}
			for(k in 0 until m){
				if((x and (1 shl k)) == 0){
					bits[k] = 0
				} else {
					bits[k] = 1
				}
			}

			var count = 0
			for(i in works){
				for(k in 0 until m){
					val dig1 = x and (1 shl k)
					val dig2 = i and (1 shl k)
					if(dig1 == dig2) count++
				}
			}

			return count

		}

		var max = 0
		var maxnum = 0
		for(k in 0 until (1 shl m)){
			var numer = getsum(k)
			if(numer > max){
				max = numer
				maxnum = k
			}
		}

		fun gcd(a : Int, b : Int){
			if(a < b){
				if(a == 0) return b
				return gcd(b%a,a)
			} else{
				if(b == 0) return a
				return (a%b,b)
			}
		}

		var gcd = gcd(max,n)
		print("Case #${q}: ")
		for(k in 0 until m){
			if((maxnum and (1 shl k)) == 0) print('F')
			else print('T')
		}

		println(" ${max/gcd}/${max/n}")

	}
}
