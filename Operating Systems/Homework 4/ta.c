/**
 * General structure of the teaching assistant.
 *
 */

#include <pthread.h>
#include <stdio.h>
#include <time.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>
#include "ta.h"

void *ta_loop(void *param)
{	
int sleep_time;
	while(1) 
	{
		//wait for students to arrive
		sem_wait(&students_waiting);
		
		//when a student shows up help the student
		srandom((unsigned)time(NULL));
		sleep_time = (int)((random() % MAX_SLEEP_TIME) + 1);
		help_student(sleep_time);
	}
}
