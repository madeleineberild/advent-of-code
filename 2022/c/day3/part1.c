#include <stdio.h>
#include <string.h>

int main() {
    char buf[64];
    long sum = 0;
    while(fgets(buf,sizeof(buf),stdin) != NULL) {

    }

    printf("\nThe accumulated score is: %ld points.\n", sum);
    return 0;
}
