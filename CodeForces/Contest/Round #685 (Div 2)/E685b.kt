import java.io.*
import java.util.*
import kotlin.math.*
//hopefully solves E2
fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))

	val n = f.readLine().toInt()

	val xors = IntArray(n){0}

	for(k in 0 until n-1){
		println("XOR ${k+1} ${k+2}")
		System.out.flush()

		xors[k] = readLine()!!.toInt()
	}

	//find array 0, which is a, array[1] is b, array[2] is c
	val xorab = xors[0]
	val xorbc = xors[1]
	val xorac = xorab xor xorbc

	println("AND 1 2")
	System.out.flush()
	val andab = readLine()!!.toInt()

	println("AND 1 3")
	System.out.flush()
	val andac = readLine()!!.toInt()


	println("OR 2 3")
	System.out.flush()
	val andbc = readLine()!!.toInt()


	var start = 0
	var i = 1
	while(i <= 100000){
		if((andbc and i) == i){											//both b and c have the bit
			if((andab and i) == i){
				start += i
			}
		} else if((xorbc and i) == i){									//exactly one of b and c has the bit
			if(((andab and i) == i) != ((andac and i) == i)){
				start += i
			}
		} else {
			if((xorab and i) == i){
				start += i
			}
		}
		i*=2
		println(start)
	}

	val tries = IntArray(n){0}
	tries[0] = start
	for(j in 1 until n){
		tries[j] = tries[j-1] xor xors[j-1]
	}

	println("! " + tries.joinToString(" "))

}
