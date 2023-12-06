#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../libs/list.h"
#include "../libs/stringlib.h"

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

    

    printf("The sum is: %ld\n", sum);
    return 0;
}
