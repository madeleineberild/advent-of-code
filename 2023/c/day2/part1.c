#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Split string at delim into n pieces
char** split(char* string, char* delim, int n) {
    int i = 0;
    char *p = strtok(string, delim);
    char **array = (char **)malloc(sizeof(char *) * (n + 1));

    while (p != NULL)
    {
        array[i] = p;
        i++;
        p = strtok(NULL, delim);
    }
    return array;
}

// Removes all instances of symbol and inserts new
void replace(char* string, char symbol, char new) {
    for(int i = 0; i < strlen(string); i++) {
        if(string[i] == symbol) {
            string[i] = new;
        }
    }
}

// Returns how many times symbol occurs in string
int count_symbol(char* string, char symbol) {
    int result = 0;
    for(int i = 0; i < strlen(string); i++) {
        if(string[i] == symbol) {
            result += 1;
        }
    }
    return result;
}

int handle_configuration(char* game_string, int config[3]) {
    int valid = 1;
    int number_of_games = count_symbol(game_string, ';') + 1;
    char** games = split(game_string, ";", number_of_games);
    for(int i = 0; i < number_of_games; i++) {
        int number_of_draws = count_symbol(games[i], ',') + 1;
        char** draws = split(games[i], ",", number_of_draws);
        for(int j = 0; j < number_of_draws; j++) {
            char** parts = split(draws[j], " ", 2);
            int number = atoi(parts[0]);
            char* color = parts[1];
            if(strcmp(color, "red") == 0) {
                if(number > config[0]) {
                    return 0;
                }
            } else if(strcmp(color, "green") == 0) {
                if(number > config[1]) {
                    return 0;
                }
            } else if(strcmp(color, "blue") == 0) {
                if(number > config[2]) {
                    return 0;
                }
            } else {
                printf("Something went wrong\n");
            }
        }
    }
    return valid;
}

int get_ID(char* string) {
    char** parts = split(string, " ", 2);
    return atoi(parts[1]);
}

int main() {
    char buf[500];
    long sum = 0;
    int red = 12;
    int green = 13;
    int blue = 14;
    int config[] = {red, green, blue};

    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        replace(buf, '\n', '\0');
        char** parts = split(buf, ":", 2);
        if(handle_configuration(parts[1], config)) {
            sum += get_ID(parts[0]);
        }
    }

    printf("The sum of the IDs of the valid games is: %ld\n", sum);
    return 0;
}
