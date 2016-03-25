/**
 * Comments go here.
 */

#include <pthread.h>
#include <stdio.h>
#include <semaphore.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include "ta.h"

pthread_t ta;
pthread_t students[NUM_OF_STUDENTS];

/**
 * Initialize all relevant data structures and
 * synchronization objects.
 */
void init()
{
	int i;
	for (i = 0; i < NUM_OF_STUDENTS; i++)
		student_id[i] = i; //assign each student thread a unique id

	//initialize students_waiting semaphore
	sem_init(&students_waiting, 0, 0); //initialized to zero because no waiting students

	//initialize chair_mutex
	pthread_mutex_init(&chair_mutex, NULL);
	seats_occupied = 0;
}

/**
 * Create the student threads.
 */
void create_students()
{
int i;

	for (i = 0; i < NUM_OF_STUDENTS; i++) {
		//fourth param is the address of the student id, to be referenced in student_loop
		pthread_create(&students[i], 0, student_loop, (void *)&student_id[i]);
	}
}

/**
 * Create the TA thread.
 */
void create_ta()
{
	pthread_create(&ta, 0, ta_loop, 0);
}

int main(void)
{
int i;

	init();

	create_ta();

	create_students();

        for (i = 0; i < NUM_OF_STUDENTS; i++)
                pthread_join(students[i], NULL);

	pthread_join(ta, NULL);

	return 0;
}
