import math
from decimal import Decimal

E = 0.000000001
pi2 = math.pi*2.0

inp = input().split()
a = Decimal(inp[0])
b = Decimal(inp[1])
c = Decimal(inp[2])

low = max(abs(a-b),max(abs(b-c),abs(a-c)));
high = min(a+b,min(b+c,a+c));

l = low
r = high
ans = -1

def calcangle(a,b,c):
   return math.acos((a*a+b*b-c*c)/(Decimal(2.0)*a*b))


for k in range(10000):
   if(l > r):
      continue
   x = l + (r-l)/Decimal(2.0)   
   
   a1 = calcangle(a,b,x)
   a2 = calcangle(b,c,x)
   a3 = calcangle(a,c,x)
         
   angle = a1+a2+a3
   if(angle < pi2-E):
      l = x
   elif(angle > pi2+E):
      r = x
   else:
      ans = x
      break
   


if(ans == -1):
   print(-1)
else:
   answer = ans*ans*Decimal(0.43301270189221932338186158537646809);
   print(answer);