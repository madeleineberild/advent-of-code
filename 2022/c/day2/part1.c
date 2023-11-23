#include <stdio.h>
#include <string.h>

int evaluate(char opponent, char choice) {
    int result;
    switch(opponent) {
        case 'A':
            if(choice == 'X') {
                result = 1 + 3;
            } else if (choice == 'Y') {
                result = 2 + 6;
            } else {
                result = 3 + 0;
            }
            break;
        case 'B':
            if(choice == 'X') {
                result = 1 + 0;
            } else if (choice == 'Y') {
                result = 2 + 3;
            } else {
                result = 3 + 6;
            }
            break;
        case 'C':
            if(choice == 'X') {
                result = 1 + 6;
            } else if (choice == 'Y') {
                result = 2 + 0;
            } else {
                result = 3 + 3;
            }
            break;
    }

    return result;
}

int main() {
    char buf[64];
    char choice = 0;
    char opponent = 0;
    long sum = 0;
    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        opponent = buf[0];
        choice = buf[2];
        sum += evaluate(opponent, choice);
    }

    printf("\nThe accumulated score is: %ld points.\n", sum);
    return 0;
}
