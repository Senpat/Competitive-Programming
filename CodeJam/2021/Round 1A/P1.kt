import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val pow10 = LongArray(12){0L}
	pow10[0] = 1L
	for(k in 1 until 12){
		pow10[k] = 10L*pow10[k-1]
	}

	for(q in 1..f.readLine().toInt()){
		val n = f.readLine().toInt()
		val array = f.readLine().split(" ").map{it.toLong()}

		val strings = Array<String>(n){j -> array[j].toString()}
		val a = IntArray(n){0}
		val b = IntArray(n){0}									//guaranteed to be <100

		//every number * 10^a + b, b < 10^a

		//1 if i1 > i2, 0 if i1 == i2, -1 if i1 < i2
		fun comp(i1 : Int, i2 : Int) : Int{
			if(strings[i1].length+a[i1] > strings[i2].length+a[i2]) return 1
			if(strings[i1].length+a[i1] < strings[i2].length+a[i2]) return -1

			val totallen = strings[i1].length+a[i1]

			for(k in 0 until totallen){
				var dig1 = 0
				var dig2 = 0

				if(k < strings[i1].length){
					dig1 = Character.getNumericValue(strings[i1].get(k))
				} else if(k == totallen-2){																//k is 2nd to last digit
					dig1 = (b[i1]%100)/10
				} else if(k == totallen-1){
					dig1 = b[i1]%10
				}

				if(k < strings[i2].length){
					dig2 = Character.getNumericValue(strings[i2].get(k))
				} else if(k == totallen-2){																//k is 2nd to last digit
					dig2 = (b[i2]%100)/10
				} else if(k == totallen-1){
					dig2 = b[i2]%10
				}

				if(dig1 > dig2) return 1
				if(dig1 < dig2) return -1
			}

			return 0

		}

		for(k in 1 until n){
			//find a
			a[k] = 0
			while(comp(k-1,k) >= 0){
				a[k]++
			}

			//a[k]-1 with higher b might be optimal
			if(a[k] > 0){
				a[k]--
				var found = false
				for(bi in 0 until min(102,pow10[min(5,a[k])].toInt())){
					b[k] = bi
					if(comp(k-1,k) < 0){
						found = true
						break
					}
				}

				if(!found){
					a[k]++
					b[k] = 0
				}
			}

		}

		val answer = a.sum()

		println("Case #${q}: $answer")
	}
}
