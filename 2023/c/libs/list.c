#include <stdio.h>
#include <stdlib.h>
#include "list.h"

list_t* new_list(char* string) {
	list_t* list;

	list = malloc(sizeof(list_t));
	list->next = list->prev = NULL;
	list->string = string;

	return list;
}

char* apply(list_t** list, int index) {
	if(index > length(list)) {
		printf("WARNING index out of bounds\n");
		return "!!!";
	}
	int i = 0;
	list_t* p = (*list);
	while(i < index) {
		p = p->next;
		i++;
	}
	return p->string;
}

void append(list_t** list, char* string) {
	list_t* new = new_list(string);
	if((*list) == NULL) {
		printf("Something went wrong in append.\n");
		(*list) = new;
	} else {
		list_t* p = (*list);
		while(p->next != NULL) {
			p = p->next;
		}
		p->next = new;
		new->prev = p;
	}
}

void free_list(list_t* list) {
	list_t* head = list;
	while(head->next != NULL) {
		list_t* p = head->next;
		free(p->string);
		head->next = p->next;
		free(p);
	}
}

size_t length(list_t** list) {
    size_t count = 0;
    list_t *p = (*list);

    while (p != NULL) {
        count++;
        p = p->next;
    }

    return count;
}

void print_list(list_t** list) {
	list_t* p = (*list);
    while(p != NULL) {
		printf("%s\n", p->string);
        p = p->next;
    }
}
