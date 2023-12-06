typedef struct list_t list_t;

struct list_t {
	list_t*		next;
	list_t*		prev;
	char*		string;
};

list_t* new_list(char* string);
void append(list_t** list, char* string);
void free_list(list_t* list);
size_t length(list_t** list);
void print_list(list_t** list);
char* apply(list_t** list, int index);