import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}

		val strings = Array<String>(n){j -> array[j].toString()}

		//1 if i1 > i2, 0 if i1 == i2, -1 if i1 < i2
		fun comp(s1 : String, s2 : String) : Int{
			if(s1.length > s2.length) return 1
			if(s1.length < s2.length) return -1

			val totallen = s1.length

			for(k in 0 until totallen){
				var dig1 = Character.getNumericValue(s1.get(k))
				var dig2 = Character.getNumericValue(s2.get(k))

				if(dig1 > dig2) return 1
				if(dig1 < dig2) return -1
			}

			return 0

		}


		var answer = 0

		for(k in 1 until n){
			if(comp(strings[k-1],strings[k]) < 0) continue

			val newstring = strings[k] + "9".repeat(strings[k-1].length-strings[k].length)
			if(comp(strings[k-1],newstring) >= 0){
				answer += strings[k-1].length-strings[k].length+1
				strings[k] = strings[k] + "0".repeat(strings[k-1].length-strings[k].length+1)
			} else {
				answer += strings[k-1].length-strings[k].length

				//find optimal value
				var i = -1
				var newdig = -1
				//for(j in strings[k].length until strings[k-1].length){
				for(j in strings[k-1].length-1 downTo strings[k].length){
					val dig = Character.getNumericValue(strings[k-1].get(j))
					if(dig < 9){
						i = j
						newdig = dig+1
						break
					}
				}

				//i is hopefully not -1
				strings[k] = strings[k] + strings[k-1].substring(strings[k].length,i) + "$newdig" + "0".repeat(newstring.length-1-i)
			}

			//println("$answer ${strings[k]}")

		}



		println("Case #${q}: $answer")
	}
}
