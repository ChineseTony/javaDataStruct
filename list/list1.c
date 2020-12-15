#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR -1

typedef int  ElemType;
typedef int  Status;
typedef struct LNode{
    ElemType data;
    struct LNode *next;
}LNode, *LinkList;

void CreateList_L(LinkList L,int n)
{
    int i;
    for(i=n;i>0;--i)
    {
        LinkList p;
        p=(LinkList)malloc(sizeof(LNode));
        scanf("%d",&p->data);
        p->next=L->next;
        L->next=p;
    }
}
Status GetElem_L(LinkList L,int i,ElemType e)
{
    LinkList p;
    int j;
    p=L->next;
    j=1;
    while(p&&j<i)
    {
        p=p->next;

        ++j;
    }
    if(!p||j>i)
    {
        return ERROR;
    }
    e=p->data;
    return OK;
}
Status ListInsert_L(LinkList L,int i,ElemType e)
{
    LinkList p,s;
    p=L;
    int j;
    j=0;
    while(p&&j<i-1)
    {
        p=p->next;
        ++j;
    }
    if(!p||j>i-1)
    {
        return ERROR;
    }
    s=(LinkList)malloc(sizeof(LNode));
    s->data=e;
    s->next=p->next;
    p->next=s;
    return OK;
}
Status ListDelete_L(LinkList L,int i,ElemType *e)
{
    LinkList p,q;
    p=L;
    int j;
    j=0;
    while(p&&j<i-1)
    {
        p=p->next;
        ++j;
    }
    if(!p||j>i-1)
    {
        return ERROR;
    }
    q=p->next;
    p->next=q->next;
    *e=q->data;
    free(q);
    return OK;

}
void  ListPrint_L(LinkList L) {
    LinkList p;
    p = L->next;
    while(p) {
        printf("%d", (p -> data));
        p = p -> next;
    }
    printf("\n");
}
void nizhuan(LinkList L)
{
    LinkList p,q,r,h;
    h=L->next;
    p=h;
    q=p->next;
    while(q!=NULL)
    {
        r=q -> next;
        q -> next=p;
        p=q;
        q=r;
    }
    h->next=NULL;
    h=p;
    L -> next=h;
}
int count1(LinkList L,ElemType e)
{
    LinkList p;
    p=L;
    int count1=0;
    while(p)
    {
        if(p->data==e){
            count1++;
        }
        p=p->next;
    }
    return count1;
}
int main()
{
    LinkList L;
    LinkList Lb;
    int temp=0;
    Lb=(LinkList)malloc(sizeof(LNode));
    Lb->next=NULL;
    L=(LinkList)malloc(sizeof(LNode));
    L->next=NULL;
    CreateList_L(L,5);
    ListInsert_L(L,6,6);
    ListDelete_L(L,2,&temp);
    ListPrint_L(L);
    nizhuan(L);
    ListPrint_L(L);

    return 0;
}
