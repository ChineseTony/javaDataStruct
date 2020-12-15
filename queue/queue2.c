#include <stdio.h>
#include <stdlib.h>

#define TURE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define INFEASIBLE -1
#define OVERFLOW -2
typedef  int  Status;
typedef int QElemType;
typedef struct QNode{
    QElemType data;
    struct QNode *next;
}QNode,*Queueptr;
typedef struct{
    Queueptr front;
    Queueptr rear;
}LinkQueue;
Status InitQueue(LinkQueue *Q)
{
    Q->front=Q->rear=(Queueptr)malloc(sizeof(QNode));
    if(!(Q->front)) exit(OVERFLOW);
    (Q->front)->next=NULL;
    return OK;
}
Status DestoryQueue(LinkQueue *Q)
{
    while(Q->front){
        Q->rear=Q->front->next;
        free(Q->front);
        Q->front=Q->rear;
    }
    return OK;
}
Status EnQueue(LinkQueue *Q,QElemType e)
{
    Queueptr p;
    p=(Queueptr)malloc(sizeof(QNode));
    if(!p) exit(OVERFLOW);
    p->data=e;
    p->next=NULL;
    Q->rear->next=p;
    Q->rear=p;
    return OK;
}
Status DeQueue(LinkQueue *Q,QElemType *e)
{
    Queueptr p;
    if(Q->front==Q->rear) return ERROR;
    p=Q->front->next;
   	*e=p->data;
    Q->front->next=p->next;
    if(Q->rear==p) Q->rear=Q->front;
    free(p);
    return OK;
}
int main()
{
    int temp=0;
    int length=0;
    LinkQueue *queue;
    queue=(LinkQueue*)malloc(sizeof(LinkQueue));
    InitQueue(queue);
    //EnQueue(queue,1);
    //EnQueue(queue,2);
   	//EnQueue(queue,3);
   DeQueue(queue,&temp);
    printf("temp=%d\n",temp);
    return 0;
}

