#include <stdio.h>
int search(int a[],int n,int key){
    int i;
    a[0]=key;
    for( i=n;!(a[i]==key);--i);
    return i;
}
int main()
{
    int n;
    int i;
    int flag;
    scanf("%d",&n);
    int a[n];
    for(i=1;i<n;i++){
        scanf("%d",&a[i]);
    }
    flag=search(a,n,4);
    if(flag==0){
        printf("没找到");
    }else{
        printf("在%d个位置",flag+1);
    }
    return 0;
}
