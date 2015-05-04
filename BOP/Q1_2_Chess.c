#include <stdio.h>

#define HALF_BYTE_LENGTH 4
#define FULL_MASK 255
#define LMASK (FULL_MASK << HALF_BYTE_LENGTH) //11110000
#define RMASK (FULL_MASK >> HALF_BYTE_LENGTH) //00001111
#define LSET(b, n) (b = (b & RMASK) | (n << HALF_BYTE_LENGTH))
#define RSET(b, n) (b = (b & LMASK) | n)
#define LGET(b) ((b & LMASK) >> HALF_BYTE_LENGTH)
#define RGET(b) (b & RMASK)

void f1();
void f2();
void f3();

int main(int argc, char* argv[]) {
	f1();
	f2();
	f3();

	return 0;
}

void f1() {
	unsigned char b;
	for (LSET(b, 1); LGET(b)<=9; LSET(b, (LGET(b)+1)))
		for (RSET(b, 1); RGET(b)<=9; RSET(b, (RGET(b)+1)))
			if ( (LGET(b))%3 != (RGET(b))%3)
				printf("A = %d, B = %d\n", LGET(b), RGET(b));
}

void f2() {
	unsigned char i = 81;
	while(i--) {
		if (i/9%3 != i%9%3) {
			printf("A = %d, B = %d\n", i/9+1, i%9+1);
		}
	}
}

void f3() {
	struct {
		unsigned char a:4;
		unsigned char b:4;
	} i;

	for (i.a=1; i.a<=9; i.a++)
		for (i.b=1; i.b<=9; i.b++)
			if (i.a%3 != i.b%3)
				printf("A = %d, B = %d\n", i.a, i.b);
}
