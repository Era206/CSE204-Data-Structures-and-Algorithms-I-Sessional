using namespace std;

class Heap{
    int *arra;
    int top =0;
    int getParentIndex(int index){
        return ((index-1)/2);
    }
    int getLeftIndex(int index){
        return (2*index+1);
    }
    int getRightIndex(int index){
        return (2*index+2);
    }

    void swap_now(int index1,int index2){
        int temp = arra[index1];
        arra[index1]=arra[index2];
        arra[index2]=temp;
    }

    void heapBottomtoTop(int index){
        int cur_index = index;
        int par_index = getParentIndex(index);
        while((arra[cur_index]>arra[par_index])&&cur_index>0){
            swap_now(cur_index, par_index);
            cur_index = par_index;
            par_index = getParentIndex(cur_index);
        }
    }
    void heapToptoBottom(int index){
        int par_index = index;
        int left_index = getLeftIndex(par_index);
        int right_index = getRightIndex(par_index);
        if(left_index>top || right_index>top)
                return;
        int position = arra[left_index]>arra[right_index] ? left_index : right_index;
        if((par_index<top)&& (arra[position] > arra[par_index])){
            swap_now(position,par_index);
            heapToptoBottom(position);
        }
    }
public:
    Heap(int num){
        arra = new int[num];
        top = 0;
    }
    ~Heap(){
        delete arra;
    }
    void insert(int value){
        arra[top]=value;
        heapBottomtoTop(top);
        top=top+1;
    }
    int deleteKey(){
        int value = arra[0];
        arra[0]= arra[top-1];
        top=top-1;
        heapToptoBottom(0);

        return value;
    }

    int getMax(){
        if(top==0) return -1;
        return arra[0];
    }
    int size(){
        return top;
    }
};
void heapsort(vector<int>&values){
        Heap hp(values.size());
        for(int i=0; i< values.size();i++){
            hp.insert(values[i]);
        }
        for(int i=0;i<values.size();i++){
            values[i]=hp.deleteKey();
        }
    }


