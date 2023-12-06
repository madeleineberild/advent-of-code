#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "../libs/stringlib.h"

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

int get_power(char* game_string) {
    int max_values[3] = {0, 0, 0}; //red, green, blue
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
                if(number > max_values[0]) {
                    max_values[0] = number;
                }
            } else if(strcmp(color, "green") == 0) {
                if(number > max_values[1]) {
                    max_values[1] = number;
                }
            } else if(strcmp(color, "blue") == 0) {
                if(number > max_values[2]) {
                    max_values[2] = number;
                }
            } else {
                printf("Something went wrong\n");
            }
        }
    }
    return max_values[0] *max_values[1] * max_values[2];
}

int main() {
    char buf[200];
    long sum = 0;

    while(fgets(buf,sizeof(buf),stdin) != NULL) {
        replace(buf, '\n', '\0');
        char** parts = split(buf, ":", 2);
        sum += get_power(parts[1]);
    }

    printf("The sum of the power of each game is: %ld\n", sum);
    return 0;
}
