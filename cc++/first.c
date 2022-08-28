#include <stdio.h>

int pos[8];
int flag[8];

void print(void) {
    int i;
    for(i=0;i < 8; i++)
        printf("%2d",pos[i]);
    putchar('\n');
}

void set(int i,int leng[],int _) {
    int j;
    for(j=0;j < 8;j++){
    printf("j = %d \n",j);
        if(!flag[i]){           //모든 행 숫자 +- 현재 행과의 차이
            pos[i] = j;
            if(i==7){
                printf("printing: \n");
                print();
            }else{
                printf("i = %d \n",i);
                int k = _ + 1;
                int leng_[k];
                
                printf("_ = %d \n",_);
                
                for (int z = 0; z < _; z++)
                    printf("leng = %d | ",leng[z]);
                printf("\n");
                for (int l = 0; l < _;l++){
                    int m = j + leng[l];
                    int x = j - leng[l];
                    printf("m = %d \n",m);
                    printf("x = %d \n",x);
                    flag[m] = 1;
                    flag[x] = 1;
                    // printf(leng_[l]+ "0");
                    // printf(leng[l] + "0");
                    leng_[l] = leng[l] + 1;
                }
                leng_[k] = 0;

                for (int z = 0; z < k; z++)
                    printf("leng_ = %d | ",leng_[z]);
                printf("\n\n");

                flag[j] = 1;
                set(i+1,leng_,_+1);
                for (int l = 0; l < _;l++){
                    int m = j + leng[l];
                    int x = j - leng[l];
                    flag[m] = 0;
                    flag[x] = 0;
                    // printf(leng_[l]+ "0");
                    // printf(leng[l] + "0");
                }
                flag[j] = 0;
            }
        }
    }
}

int main(){
    int i;
    for(i=0; i < 8; i++)
        flag[i] = 0;
    
   int lengs[1] = {0};
   set(0,lengs,1);
    return 0;
}