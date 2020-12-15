#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR -1
#define OVERFLOW -2
#define MAXSIZE 100
typedef int Status;
typedef int ElemType;
typedef struct
{
    ElemType *base;
    int front;
    int rear;
}SqQueue;
Status InitQueue(SqQueue *Q)
{

    Q->base=(ElemType *)malloc(MAXSIZE*sizeof(ElemType));
    if(!Q->base) exit(OVERFLOW);
    Q->front=Q->rear=0;
    return OK;
}
Status QueueLength(SqQueue *Q)
{
    return (Q->rear-Q->front+MAXSIZE)% MAXSIZE ;
}
Status EnQueue(SqQueue *Q,ElemType e)
{
    if((Q->rear+1)%MAXSIZE==Q->front) return ERROR;
    Q->base[Q->rear]=e;
    Q->rear=(Q->rear+1)%MAXSIZE;
    return OK;
}
Status DeQueue(SqQueue *Q,ElemType *e)
{
    if(Q->front==Q->rear) return ERROR;
    *e=Q->base[Q->front];
    Q->front=(Q->front+1)%MAXSIZE;
    return OK;
}
int main()
{
    SqQueue *Q;
  	Q=(SqQueue*)malloc(sizeof(SqQueue));
    int temp=0;
    InitQueue(Q);
    EnQueue(Q,1);
    EnQueue(Q,2);
    EnQueue(Q,3);
    DeQueue(Q,&temp);
    printf("temp=%d\n",temp);
    return 0;

}
