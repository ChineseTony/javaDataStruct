#include <stdio.h>
#include <stdlib.h>
#define TURE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define INFEASIBLE -1
#define OVERFLOW -2

typedef int Status;
typedef  int KeyType;
typedef struct BiTNode{
    KeyType  data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;
Status Visit(KeyType e)
{
    printf("%d\n",e);
    return OK;
}
Status SearchBST(BiTree T,KeyType key,BiTree f,BiTree *p) {
    if (!T) {
        *p = f;
        return FALSE;
    } else if (key == (T->data)) {
        *p=T;
        return TURE;
    } else if(key<(T->data)){
        return SearchBST(T->lchild,key,T,p);
    }else{
        return  SearchBST(T->rchild,key,T,p);
    }
}
Status insertBSF(BiTree *T,KeyType keyType){
    BiTree p,s;
    if(!SearchBST(*T,keyType, NULL,&p)){
        s=(BiTree)malloc(sizeof(BiTNode));
        s->data=keyType;
        s->lchild=NULL;
        s->rchild=NULL;
        if(!p){
            *T=s;
        }else if(keyType < p->data){
            p->lchild=s;
        }else{
            p->rchild=s;
        }
        return  TURE;
    } else{
        return FALSE;
    }
}

Status InOrderTraverse(BiTree T,Status(*Visit)(KeyType e))
{
    if(T){
        if(InOrderTraverse(T->lchild,Visit))
            if(Visit(T->data))
                if(InOrderTraverse(T->rchild,Visit)) return OK;
        return ERROR;
    }else {
        return OK;
    }
}
Status Delete(BiTree *p){
    BiTree q,s;
    if(!(*p)->rchild){
        q=*p;
        *p=(*p)->lchild;
        free(q);
    }else if(!(*p)->lchild){
        q=*p;
        *p=(*p)->rchild;
        free(q);
    }else{
        q=*p;
        s=(*p)->lchild;
        while(s->rchild){
            q=s;
            s=s->rchild;
        }
        (*p)->data=s->data;
        if(q!=(*p)){
            q->rchild=s->lchild;
        }else{
            q->lchild=s->lchild;
        }
        free(s);
    }
    return TURE;
}
Status DeleteBSF(BiTree T,KeyType key){
    if(!T){
        return FALSE;
    }else{
        if(key == T->data){
            return Delete(&T);
        }else if(key  < (T->data)){
            return DeleteBSF(T->lchild,key);
        }else{
            return DeleteBSF(T->rchild,key);
        }
    }
}
int main()
{
    BiTree T=NULL;
    int n;
    printf("请输入节点的个数:");
    scanf("%d",&n);
    int a[n];
    int i,j,k;
    for(i=0;i<n;i++){
        printf("请输入节点的值:");
        scanf("%d",&a[i]);
    }
    for(j=0;j<n;j++){
        insertBSF(&T,a[j]);
    }
    InOrderTraverse(T,Visit);
    printf("############\n");
    printf("please input the delete node:");
    scanf("%d",&k);

    DeleteBSF(T,k);
    InOrderTraverse(T,Visit);


    return  0;
}
