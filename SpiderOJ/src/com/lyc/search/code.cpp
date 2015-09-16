#include <stdio.h>
#include <stdlib.h>

int main()
{
    int a,b,d=0;
    while(scanf("%d%d",&a,&b)!=EOF){
        d=a+b;
        printf("%d\n",d);
    }
    return 0;
}