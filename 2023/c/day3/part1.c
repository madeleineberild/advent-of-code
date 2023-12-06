#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../libs/list.h"
#include "../libs/stringlib.h"

struct pos_list_t {
    pos_list_t* next;
    long x;
    long y;
}
typedef struct pos_list_t pos_list_t;

struct item_t {
    pos_list_t* pos_list;
    item_t* next;
    long number;
    int valid;
}
typedef struct item_t item_t;

item_t* create_action_items(list_t** list) {

}

void check_validity(item_t* items) {
    
}

int main() {
    char buf[64];
    long sum = 0;
    int rows = 0;
    list_t* list = NULL;

    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        replace(buf, '\n', '\0');
        char* string = (char *)malloc(strlen(buf) + 1);
        strcpy(string, buf);
        if(list == NULL) {
            list = new_list(string);
        } else {
            append(&list, string);
        }
    }

    item_t* items = create_action_items(&list);
    check_validity(items);

    item_t* p = &items;
    while(p != NULL) {
        if(p->valid) {
            sum += p->number;
        }
        p = p->next;
    }

    free_list(list);
    printf("The sum is: %ld\n", sum);
    return 0;
}
