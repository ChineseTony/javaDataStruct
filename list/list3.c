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
Status InitList_Sq(SqList *L)//��ʼ��
{
    L->elem=(Elemtype *)malloc(LIST_INIT_SIZE*sizeof(Elemtype));
    if(!L->elem) exit(OVERFLOW);
    L->length=0;
    L->listsize=LIST_INIT_SIZE;
    return OK;
}
Status ListInsert_Sq(SqList *L,int i,Elemtype e)//�ڵ�i��Ԫ��ǰ����e
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
Status ListDelete_Sq(SqList *L,int i,Elemtype e)//ɾ����i��Ԫ��
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
void ListTraverse(SqList *L)//����˳���
{
    int i;
    for(i=0;i<L->length;i++)
    {
        printf("%d\n",L->elem[i]);
    }
}
void clearList(SqList *L)//�����Ա��ÿ�
{
    L->length=0;
}
Status ListEmpty(SqList *L)//�ж����Ա��Ƿ�Ϊ��
{
    return (L->length==0)?TURE: FALSE;
}
Status GetElem(SqList *L,Elemtype i,Elemtype e) //��ȡ��i��Ԫ�ص�ֵ������i��Ԫ�ص�ֵ��ֵ��e
{
    if (i < 0 || i > L->length) {
        return FALSE;
    }else{
        e=L->elem[i-1];
        return TURE;
    }

}
Status  PriorElem(SqList *L,Elemtype  currentElem,Elemtype *preElem)
{//��ȡ��ǰԪ�ص�ǰ������ֵ��preElem
    if(currentElem<1||currentElem>L->length)
    {
        return FALSE;
    }else{
        *preElem=L->elem[currentElem-2];
        return TURE;
    }
}
Status NextElem(SqList *L,Elemtype currentElem,Elemtype *nextElem)
{//��ȡ���Ա�ĺ�̸�ֵ��nextElem
    if(currentElem>=L->length||currentElem<1)
    {
        return FALSE;
    }else{
        *nextElem=L->elem[currentElem];
    }
}
Status del(SqList *L,Elemtype i,Elemtype k)//ɾ�����Ա��i��Ԫ��������k��Ԫ��
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
