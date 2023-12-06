typedef struct list_t list_t;

struct list_t {
	list_t*		succ;
	list_t*		pred;
	char*		string;
};

list_t* new_list(char* string);
void free_list(list_t**);
