#include <stdio.h>
#include <stdlib.h>
#include "list.h"

list_t* new_list(char* string)
{
	list_t* list;

	list = malloc(sizeof(list_t));
	list->succ = list->pred = list;
	list->string = string;

	return list;
}

void free_list(list_t** list) {
	if(*list == NULL){
		return;
	}

	while(*list != (*list)->succ){
		list->pred->succ = list->succ;
		list->succ->pred = list->pred;
		free(list->string);
		free(list);
	}
}
