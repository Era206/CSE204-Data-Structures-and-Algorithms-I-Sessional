package mypackage;

class mergeSort_y{
    public static House mArray[] ;
    public int len ;
    public mergeSort_y(int n, House arr[]){
        mArray = new House[n];
        for(int i=0; i<n; i++){
            mArray[i]= new House(0,0, i);
        }
        len = n;
        for(int i=0; i < n; i++){
            mArray[i]=arr[i];
        }
        mSort(mArray);
    }
    public void merge(House l[], House r[], House a[]){
        int len_l = l.length;
        int len_r = r.length;
        int i=0, j=0, k=0;
        while(i < len_l && j < len_r){
            if(l[i].y<=r[j].y){
                a[k]=l[i];
                i++;
            }
            else{
                a[k]= r[j];
                j++;
            }
            k++;
        }
        while(i < len_l){
            a[k]=l[i];
            i++;
            k++;
        }
        while(j < len_r){
            a[k]=r[j];
            j++;
            k++;
        }
    }

    public void mSort(House A[]){
        int n= A.length;
        if(n<2) return;
        int mid = n/2;
        House[]left = new House[mid];
        for(int i=0; i<mid; i++){
            left[i] = new House(0, 0,i);
        }
        House[]right = new House[n-mid];
        for(int i=0; i< (n-mid); i++){
            right[i] = new House(0, 0, i);
        }
        for(int i=0; i<mid; i++){
            //left[i].y=A[i].y;
            left[i]=A[i];
        }
        for(int j=mid; j <n; j++){
           // right[j-mid].y=A[j].y;
            right[j-mid]=A[j];
        }
        mSort(left);
        mSort(right);
        merge(left, right, A);
    }


}

class mergeSort_x{
    public static House mArray[] ;
    public int len ;
    public mergeSort_x(int n, House arr[]){
        mArray = new House[n];
        for(int i=0; i<n; i++){
            mArray[i]= new House(0,0, i);
        }
        len = n;
        for(int i=0; i < n; i++){
            mArray[i]=arr[i];
        }
        mSort(mArray);
    }
    public void merge(House l[], House r[], House a[]){
        int len_l = l.length;
        int len_r = r.length;
        int i=0, j=0, k=0;
        while(i < len_l && j < len_r){
            if(l[i].x<=r[j].x){
                a[k]=l[i];
                i++;
            }
            else{
                a[k]= r[j];
                j++;
            }
            k++;
        }
        while(i < len_l){
            a[k]=l[i];
            i++;
            k++;
        }
        while(j < len_r){
            a[k]=r[j];
            j++;
            k++;
        }
    }

    public void mSort(House A[]){
        int n= A.length;
        if(n<2) return;
        int mid = n/2;
        House[]left = new House[mid];
        for(int i=0; i<mid; i++){
            left[i] = new House(0, 0,i);
        }
        House[]right = new House[n-mid];
        for(int i=0; i< (n-mid); i++){
            right[i] = new House(0, 0, i);
        }
        for(int i=0; i<mid; i++){
            //left[i].y=A[i].y;
            left[i]=A[i];
        }
        for(int j=mid; j <n; j++){
            // right[j-mid].y=A[j].y;
            right[j-mid]=A[j];
        }
        mSort(left);
        mSort(right);
        merge(left, right, A);
    }


}

