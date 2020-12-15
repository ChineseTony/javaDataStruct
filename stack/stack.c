#include <stdio.h>
#include <stdlib.h>

#define STACK_INIT_SIZE 100
#define STACKINCREMEANT 10
#define TURE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define INFEASIBLE (-1)
#define OVERFLOW (-2)

typedef int ElemType;
typedef int Status;
typedef struct{
    ElemType *top;
    ElemType *base;
    int stacksize;
}SqStack;
Status InitStack(SqStack *S)
{
    S->base=(ElemType *)malloc(STACK_INIT_SIZE*sizeof(SqStack));
    S->top=S->base;
    S->stacksize=STACK_INIT_SIZE;
    return OK;
}
Status push(SqStack *S,ElemType e)
{
if(((S->top)-(S->base))>=S->stacksize)
{
S->base=(ElemType *)realloc(S->base,(S->stacksize+STACKINCREMEANT)*sizeof(ElemType));
if(!S->base)exit(OVERFLOW);
S->top=S->base+S->stacksize;
S->stacksize+= STACKINCREMEANT;
}
*S->top++=e;
return OK;
}
Status pop(SqStack *S,ElemType e)
{

if(S->top==S->base){
return ERROR;
}
e=*--S->top;
return OK;
}
Status peek(SqStack *S,ElemType *e)
{
    if(S->top==S->base)
    {
        return ERROR;
    }
    *e=*(S->top-1);
    return OK;
}
int main()
{
    SqStack S;
    int temp=0;
    InitStack(&S);
    push(&S,1);
    push(&S,2);
    push(&S,3);
    peek(&S,&temp);
    printf("temp=%d\n",temp);
    return 0;
}
