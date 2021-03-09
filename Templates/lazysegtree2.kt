import java.io.*
import java.util.*
import kotlin.math.*

fun main(){
	val f = BufferedReader(InputStreamReader(System.`in`))


}

public class SegmentTreeFast(val n : Int){

	// generic code
	val value: LongArray
	val deltas: LongArray // delta[i] affects value[i], delta[2*i+1] and delta[2*i+2]

	init {
	  value = LongArray(2 * n)
	  for (i in 0 until n) {
		 value[i + n] = getInitValue()
	  }
	 for(i in (2*n-1) downTo 2 step 2){
		 value[i shr 1] = queryOperation(value[i], value[i xor 1])
	  }
	  deltas = LongArray(2*n){getNeutralDelta()}
	}

  // Modify the following 5 methods to implement your custom operations on the tree.
  // This example implements Add/Sum operations. Operations like Add/Max, Set/Max can also be implemented.
  //CURRENTLY SET/SUM
  fun modifyOperation(x : Long, y : Long) : Long{
    return y;
  }

  // query (or combine) operation
  fun queryOperation(leftValue : Long, rightValue : Long) : Long{
    return leftValue + rightValue;
  }

  fun deltaEffectOnSegment(delta : Long, segmentLength : Int) : Long{
    if (delta == getNeutralDelta()) return getNeutralDelta();
    // Here you must write a fast equivalent of following slow code:
    // int result = delta;
    // for (int i = 1; i < segmentLength; i++) result = queryOperation(result, delta);
    // return result;
    return delta*segmentLength.toLong();
  }

  fun getNeutralDelta() : Long{
    return -1L;
  }

  fun getInitValue() : Long{
    return 0L;
  }

  fun joinValueWithDelta(value : Long, delta : Long) : Long{
    if (delta == getNeutralDelta()) return value;
    return modifyOperation(value, delta);
  }

  fun joinDeltas(delta1 : Long, delta2 : Long) : Long{
    if (delta1 == getNeutralDelta()) return delta2;
    if (delta2 == getNeutralDelta()) return delta1;
    return modifyOperation(delta1, delta2);
  }

  fun pushDelta(i : Int) {
    var d = 0;
	 while((i shr d) > 0) d++
	 d-=2
	 while(d >= 0){
      val  x = i shr d;
      value[x shr 1] = joinNodeValueWithDelta(x shr 1, 1 shl (d + 1));
      deltas[x] = joinDeltas(deltas[x], deltas[x shr 1]);
      deltas[x xor 1] = joinDeltas(deltas[x xor 1], deltas[x shr 1]);
      deltas[x shr 1] = getNeutralDelta();
		d--
    }
  }



  fun joinNodeValueWithDelta( i : Int, len : Int) : Long{
    return joinValueWithDelta(value[i], deltaEffectOnSegment(deltas[i], len));
  }

  fun query(from1 : Int, to1 : Int) : Long{
	 var from = from1
	 var to = to1
    from += value.size shr 1;
    to += value.size shr 1;
    pushDelta(from);
    pushDelta(to);
    var res = 0L;
    var found = false;
	 var len = 1
	 while(from <= to){
    //for (int len = 1; from <= to; from = (from + 1) >> 1, to = (to - 1) >> 1, len <<= 1) {
      if ((from and 1) != 0) {
        res = if(found) queryOperation(res, joinNodeValueWithDelta(from, len)) else joinNodeValueWithDelta(from, len)
        found = true;
      }
      if ((to and 1) == 0) {
        res = if(found) queryOperation(res, joinNodeValueWithDelta(to, len)) else joinNodeValueWithDelta(to, len)
        found = true;
      }
		from = (from + 1) shr 1
		to = (to - 1) shr 1
		len = len shl 1
    }
    return res;
  }

  fun modify(from1 : Int, to1 : Int, delta : Long) {
	 var from = from1
	 var to = to1
    from += value.size shr 1;
    to += value.size shr 1;
    pushDelta(from);
    pushDelta(to);
    var a = from;
    var b = to;
	 while(from <= to){
	 //for (; from <= to; from = (from + 1) >> 1, to = (to - 1) >> 1) {
      if ((from and 1) != 0) {
        deltas[from] = joinDeltas(deltas[from], delta);
      }
      if ((to and 1) == 0) {
        deltas[to] = joinDeltas(deltas[to], delta);
      }
		from = (from + 1) shr 1
		to = (to - 1) shr 1
    }
	 var i = a
	 var len = 1
	 while(i > 1){
	 //for (int i = a, len = 1; i > 1; i >>= 1, len <<= 1) {
      value[i shr 1] = queryOperation(joinNodeValueWithDelta(i, len), joinNodeValueWithDelta(i xor 1, len));
		i = i shr 1
		len = len shl 1
    }
	 i = b
	 len = 1
	 while(i > 1){
    //for (int i = b, len = 1; i > 1; i >>= 1, len <<= 1) {
      value[i shr 1] = queryOperation(joinNodeValueWithDelta(i, len), joinNodeValueWithDelta(i xor 1, len));
		i = i shr 1
		len = len shl 1
    }
  }
}
