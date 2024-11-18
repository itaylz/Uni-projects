#include <stdio.h>
#include <assert.h>
#define LIMIT 100000
#define MAX_HEAP_SIZE 2056

size_t heap_size = 0; //total memory used
char memory_allocated[LIMIT] = {0};  //allocated memory in the stack in the form of
                                     //an array acting as a virtual heap

//struct for allocating chunks of memory in memory_allocated array
typedef struct {
    void *start;
    size_t size;
}memory_chunk;

memory_chunk allocated_chunk[MAX_HEAP_SIZE] = {0};  //keeps track of allocated chunks
size_t memory_used = 0; //chunk memory used


//allocates memory returns void pointer to memory addresses allocated
void *alloc(size_t sz){
    //check if user input size exceeds LIMIT
    assert(heap_size + sz <= LIMIT);
    //if size input 0 or smaller invalid
    if(sz <= 0){
        printf("invalid input size");
    }

    //pointer for allocated memory
    void *allocated = memory_allocated + heap_size;
    heap_size += sz;

    //the allocated chunk of memory
    const memory_chunk chunk = {
            .start = allocated,
            .size = sz,
    };

    //check if memory_used (allocated chunk space) exceeds the set MAX
    assert(memory_used <= MAX_HEAP_SIZE);
    //if assertion passed chunk allocated is then appended and memory used increases
    allocated_chunk[memory_used++] = chunk;

    return allocated;
}

void free(void *ptr){
    ;
}


int main(void) {
    ;
    return 0;
}
