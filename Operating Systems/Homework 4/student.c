/**
 * General structure of a student.
 *
 */

#include <pthread.h>
#include <stdio.h>
#include <time.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include "ta.h"

void *student_loop(void *param)
{	
int number = *((int *)param); //dereference *param as an integer
int sleep_time;
	while(1) 
	{
		srandom((unsigned)time(NULL));
		sleep_time = (int)((random() % MAX_SLEEP_TIME) + 1);
		printf("Student %d is programming\n", number);
		programming(sleep_time);

		
		pthread_mutex_lock(&chair_mutex);
		//check if space available
		if (seats_occupied < MAX_WAITING_STUDENTS) {
			//ask for help from TA
			sem_post(&students_waiting);
			seats_occupied++;
			printf("Student %d asked for help. Students waiting: %d\n", number, seats_occupied);
		}
		pthread_mutex_unlock(&chair_mutex);
	}
	
	return NULL;
}
