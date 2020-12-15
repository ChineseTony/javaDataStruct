#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0
#define INFEASIBLE (-1)
#define OVERFLOW (-2)

typedef int Status;
typedef  char TElemType;
typedef struct BiTNode{
    TElemType  data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;
Status Visit(TElemType e)
{
    printf("%c\n",e);
    return OK;
}
Status CreateBiTree(BiTree * T)
{
    char ch;
    scanf("%c",&ch);
    if(ch=='#') {
        *T=NULL;
    }else {
        if (!( *T = (BiTNode *) malloc(sizeof(BiTNode)))) {
            exit(OVERFLOW);
        } else {
            (*T)->data = ch;
            CreateBiTree( &(*T) -> lchild);
            CreateBiTree( &(*T) -> rchild);
        }
    }
    return OK;
}
Status  PreorderTraverse(BiTree T,Status(* Visit)(TElemType e)) {
    if (T) {
        if (Visit(T->data)) {
            if (PreorderTraverse(T->lchild, Visit))
                if (PreorderTraverse(T->rchild, Visit))return OK;
        }
        return ERROR;
    } else{
        return OK;
    }
}
Status InOrderTraverse(BiTree T,Status(*Visit)(TElemType e))
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
Status PostTraverse(BiTree T,Status(*Visit)(TElemType e))
{
    if(T){
        if(InOrderTraverse(T->rchild,Visit))
            if(Visit(T->data))
                if(InOrderTraverse(T->lchild,Visit)) return OK;
        return ERROR;
    }else {
        return OK;
    }
}
int leafCount(BiTree T){
    int leaf=0;
    if(T == NULL){
        return 0;
    }else if(T->lchild==NULL && T->rchild==NULL){
        return 1;
    }else{
        leaf=leafCount(T->rchild)+leafCount(T->lchild);
        return leaf;
    }
}
int main()
{
    BiTree T = NULL ;
    CreateBiTree(&T);
    PreorderTraverse(T,Visit);
    printf("############\n");
    InOrderTraverse(T,Visit);
    printf("############\n");
    PostTraverse(T,Visit);
    int leaf=leafCount(T);
    printf("############\n");
    printf("leaf=%d",leaf);
    return  0;
}
