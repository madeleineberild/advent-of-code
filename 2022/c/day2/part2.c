#include <stdio.h>
#include <string.h>

int evaluate(char opponent, char end) {
    int result;
    switch(opponent) {
        case 'A': //rock
            if(end == 'X') { //loss
                result = 0 + 3;
            } else if (end == 'Y') { //draw
                result = 3 + 1;
            } else { //win
                result = 6 + 2;
            }
            break;
        case 'B': //paper
            if(end == 'X') {
                result = 0 + 1;
            } else if (end == 'Y') {
                result = 3 + 2;
            } else {
                result = 6 + 3;
            }
            break;
        case 'C': //scissors
            if(end == 'X') {
                result = 0 + 2;
            } else if (end == 'Y') {
                result = 3 + 3;
            } else {
                result = 6 + 1;
            }
            break;
    }

    return result;
}

int main() {
    char buf[64];
    char end = 0;
    char opponent = 0;
    long sum = 0;
    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        opponent = buf[0];
        end = buf[2];
        sum += evaluate(opponent, end);
    }

    printf("\nThe accumulated score is: %ld points.\n", sum);
    return 0;
}
