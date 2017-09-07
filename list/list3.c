#include <stdio.h>
#include <stdlib.h>

#define LIST_INIT_SIZE 100
#define LISTINCREMEANT 10
#define TURE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define INFEASIBLE -1
#define OVERFLOW -2
typedef int Elemtype;
typedef int Status;
typedef struct{
    int *elem;
    int length;
    int listsize;
}SqList;
Status InitList_Sq(SqList *L)//初始化
{
    L->elem=(Elemtype *)malloc(LIST_INIT_SIZE*sizeof(Elemtype));
    if(!L->elem) exit(OVERFLOW);
    L->length=0;
    L->listsize=LIST_INIT_SIZE;
    return OK;
}
Status ListInsert_Sq(SqList *L,int i,Elemtype e)//在第i个元素前插入e
{
    if(i<1||i>L->length+1)
    return ERROR;
    if((L->length)>=(L->listsize))
    {
        Elemtype *newbase=(Elemtype *)realloc(L->elem,
                                      (L->listsize+LISTINCREMEANT)*sizeof(Elemtype));
    if(!newbase) exit(OVERFLOW);
    L->elem=newbase;
    L->listsize+=LISTINCREMEANT;
    }
    Elemtype *q,*p;
    q=&(L->elem[i-1]);
    for(p=&(L->elem[L->length-1]);p>=q;--p)
    {
        *(p+1)=*p;
    }
        *q=e;
    ++L->length;
    return OK;
}
Status ListDelete_Sq(SqList *L,int i,Elemtype e)//删除第i个元素
{
    if(i<1||(i>L->length)) return ERROR;
    Elemtype *p,*q;
    p=&(L->elem[i-1]);
    e=*p;
    q=L->elem+L->length-1;
    for(++p;p<=q;++p)
    {
        *(p-1)=*p;
    }
    --L->length;
    return OK;
}
void ListTraverse(SqList *L)//遍历顺序表
{
    int i;
    for(i=0;i<L->length;i++)
    {
        printf("%d\n",L->elem[i]);
    }
}
void clearList(SqList *L)//将线性表置空
{
    L->length=0;
}
Status ListEmpty(SqList *L)//判断线性表是否为空
{
    return (L->length==0)?TURE: FALSE;
}
Status GetElem(SqList *L,Elemtype i,Elemtype e) //获取第i个元素的值，将第i个元素的值赋值给e
{
    if (i < 0 || i > L->length) {
        return FALSE;
    }else{
        e=L->elem[i-1];
        return TURE;
    }

}
Status  PriorElem(SqList *L,Elemtype  currentElem,Elemtype *preElem)
{//获取当前元素的前驱，赋值给preElem
    if(currentElem<1||currentElem>L->length)
    {
        return FALSE;
    }else{
        *preElem=L->elem[currentElem-2];
        return TURE;
    }
}
Status NextElem(SqList *L,Elemtype currentElem,Elemtype *nextElem)
{//获取线性表的后继赋值给nextElem
    if(currentElem>=L->length||currentElem<1)
    {
        return FALSE;
    }else{
        *nextElem=L->elem[currentElem];
    }
}
Status del(SqList *L,Elemtype i,Elemtype k)//删除线性表第i个元素起连续k个元素
{
    if(i>((L->length)-k)||i<0) return ERROR;
    int j;
    for(j=0;j<k;j++)
    {
        Elemtype *p,*q;
        p=&(L->elem[i-1]);
        q=L->elem+L->length-1;
        for(++p;p<=q;++p)
        {
            *(p-1)=*p;
        }
    }
    L->length=(L->length)-k;
    return OK;
}

int main()
{
    SqList L;
    InitList_Sq(&L);
    ListInsert_Sq(&L,1,1);
    ListInsert_Sq(&L,2,2);
    ListInsert_Sq(&L,3,3);
    ListInsert_Sq(&L,4,4);
    ListInsert_Sq(&L,5,5);
    ListTraverse(&L);
    int temp;
  //  temp=L.length;
  //  printf("temp=%d\n",temp);
   // int c;
   // GetElem(&L,1,c);
    //printf("c=%d\n",c);
 //   ListDelete_Sq(&L,2,temp);
  //  printf("temp=%d\n",temp);
    PriorElem(&L,2,&temp);
    printf("temp=%d\n",temp);
    NextElem(&L,2,&temp);
    printf("temp=%d\n",temp);
    del(&L,2,3);
    ListTraverse(&L);

    return 0;
}
