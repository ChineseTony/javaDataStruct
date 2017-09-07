#include <stdio.h>

int search(int a[],int n,int key){
    int mid;
    int low=0;
    int high=n;
    while(low<high){
        mid=(low+high)/2;
        if(a[mid]==key){
            return mid;
        }else if(a[mid]<key){
                low=mid+1;
        }else{
                high=mid-1;
        }
    }
    return 0;
}
int compInc(const void *a, const void *b)
{
    return *(int *)a - *(int *)b;
}
int main()
{
    int n,i,flag;
    scanf("%d",&n);
    int a[n];
    for(i=0;i<n;i++){
        scanf("%d",&a[i]);
    }
	qsort(a, n, sizeof(a[0]), compInc);
    flag=search(a,n,4);
    if(flag==0){
        printf("没找到");
    }else{
        printf("在第个%d位置",flag+1);
    }

    return 0;
}
