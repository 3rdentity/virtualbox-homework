/**
 * Program that uses threads to estimate pi
 * @author Jeremy Dormitzer
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <sys/time.h>
#include <pthread.h>

#define NUM_COUNT 1000000

double in_count = 0;
double total_count = 0; //although this is the same as NUM_COUNT in this implementation,
			//it will be needed when the application creates multiple threads

void *runner(); //the thread

int main(int argc, char *argv[]) {
	//seed random number generator
	srandom((unsigned)time(NULL));

	pthread_t tid;
	pthread_attr_t attr;

	pthread_attr_init(&attr);
	pthread_create(&tid, &attr, runner, NULL);

	pthread_join(tid, NULL);

	double pi = 4 * in_count / total_count;
	fprintf(stdout, "Pi is %f", pi);

	return 0;
}

void *runner() {
	int i;
	for (i = 0; i < NUM_COUNT; i++) {
		total_count++;
		double x, y;
		x = random() / ((double)RAND_MAX + 1) * 2.0 - 1.0;
		y = random() / ((double)RAND_MAX + 1) * 2.0 - 1.0;
		//check if (x,y) is in unit circle
		if (sqrt(x*x + y*y) < 1.0) {
			in_count++;
		}
	}
}
