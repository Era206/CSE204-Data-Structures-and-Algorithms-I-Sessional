package mypackage;

import java.util.Scanner;

public class expression {
    public static boolean isValid(String s){
        char[] st = s.toCharArray();
        int len=st.length;
        int open=0;
        int close=0;
        boolean flag=false;
        for(int i=0; i<len;i++){
           if(st[i]=='(' || st[i]==')') {
               if(st[i]=='('){
                   open+=1;

                   if(i!=len-1 && (st[i+1]=='+' || st[i+1]=='*' || st[i+1]=='/')){
                       System.out.println(st[i+1] + " is not supported after" +'(');
                       return false;
                   }

                    else if(i!=len-1 && st[i+1]==')'){
                       System.out.println(st[i+1] + " is not supported after" +'(');
                       return false;
                   }

                   else if(i!=0 && (st[i-1]>='0' && st[i-1]<='9')){
                       System.out.println("any operand is not supported before ( ");
                       return false;
                   }

                    else if((i+1)!=len-1 && st[i+1]=='-'){
                        if(st[i+2]>='0'&&st[i+2]<='9') flag = true;
                       else if(st[i+2]=='('){
                           flag = true;
                       }
                        else{
                            System.out.println(st[i+1] + " is not supported after" +'(');
                            return false;
                        }
                   }

                    else if(i==len-1){
                       System.out.println('(' + " is not supported in the end");
                       return false;
                   }

                    else
                        flag = true;
               }
               else{
                   close+=1;

                   if(i!=len-1 && (st[i+1]>='0'&&st[i+1]<='9')) {
                       System.out.println("any operand is not supported after" + ')');
                       return false;
                   }
                   else if(i!=0 && (st[i-1]=='+'|| st[i-1]=='-'||st[i-1]=='*'||st[i-1]=='/')){
                       System.out.println( "any operator in this case " + st[i-1] +" is not supported before" +')');
                       return false;
                   }

                   if(i==0){
                       System.out.println("closing parenthesis cannot be the 1st member!");
                       return false;
                   }

                   else flag=true;
               }
           }
           else if(st[i]=='+' || st[i]=='-'|| st[i]=='*'|| st[i]=='/'){

                if(i!=len-1 && (st[i+1]=='+'||st[i+1]=='-'||st[i+1]=='*'|| st[i+1]=='/')){
                    System.out.println("two operator cannot be placed one after another!");
                    return false;
                }

                else if((i==0||i==len-1)){
                    System.out.println("operator cannot be the 1st or last element!");
                    return false;
                }

                else if(i!=len-1 &&  st[i]=='/'){
                    if(st[i+1]=='0'){
                        if((i+1!=len-1) &&(st[i+2]>='0' && st[i+2]<='9'))
                            flag = true;
                        else {
                            System.out.println("0 cannot divide any number!");
                            return false;
                    }
                    }
                    else flag = true;
                }

                else flag=true;
           }
           else if(st[i]>='0'&&st[i]<='9'){
               flag=true;
           }
           else{
               System.out.println("doesn't match with required process!");
              return false;
           }

           if(close>open) {
               System.out.println("closing parenthesis cannot be greater than opening parenthesis!");
               return false;
           }
        }
        if(open!=close) {
            System.out.println("parenthesis number mismatched!");
            return false;
        }
        return flag;
    }

    public static queue<Character> inToPostFix(String expression)
    {
        char[] tokens = expression.toCharArray();
        int len = tokens.length;

        queue<Character> values = new queue<Character>();

        stack<Character> operator = new stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {

            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' && tokens[i] <= '9')
            {

                if(i!=len-1 &&(tokens[i+1]>='0'&&tokens[i+1]<='9')){
                    values.enQueue('(');
                    while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9'){
                        values.enQueue(tokens[i++]);
                    }
                    values.enQueue(')');
                }
                else{
                    values.enQueue(tokens[i++]);
                }

                i--;
            }

            else if (tokens[i] == '('){
                if(tokens[i+1]=='-') {
                    values.enQueue('0');
                }
                operator.push(tokens[i]);
            }


            else if (tokens[i] == ')')
            {
                while (operator.peekStack() != '(')
                    values.enQueue(operator.pop());
                operator.pop();
            }

            else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
            {

                while (!operator.isEmpty() && hasPrecedence(tokens[i], operator.peekStack()))
                    values.enQueue(operator.pop());

                operator.push(tokens[i]);
            }
        }


        while (!operator.isEmpty())
            values.enQueue(operator.pop());

        return values;
    }

    public static int getAns(queue<Character> q){

        stack<Integer> ans = new stack<Integer>();

        while(!q.isEmpty()){

            if(q.peekQueue()=='+'||q.peekQueue()=='-'||q.peekQueue()=='*'||q.peekQueue()=='/'){
                ans.push(getTot(q.deQueue(), ans.pop(), ans.pop()));
            }

            else if(q.peekQueue()>='0'&&q.peekQueue()<='9'){
                StringBuffer sb = new StringBuffer();
                sb.append(q.deQueue());
                ans.push(Integer.parseInt(sb.toString()));
            }

            else if(q.peekQueue()=='('){
                q.deQueue();
                StringBuffer sb = new StringBuffer();

                while(q.peekQueue()!=')'){
                    sb.append(q.deQueue());
                }
                q.deQueue();
                ans.push(Integer.parseInt(sb.toString()));
            }
        }

        return ans.peekStack();
    }

    public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static int getTot(char op, int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter expression: ");
        String s = sc.next();
        if(isValid(s)){
            System.out.println("valid expression");
            System.out.println(getAns(inToPostFix(s)));
        }
        else System.out.println("not valid");
    }

}
