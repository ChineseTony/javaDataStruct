#include <stdio.h>
#include <stdlib.h>
#define TRUE
#define FALSE 0
#define OK 1
#define ERROR -1
typedef int ElemType;
typedef struct DuLNode{
    ElemType data;
    struct DuLNode *prior;
    struct DuLNode *next;
}DuLNode,*DuLinkList;
