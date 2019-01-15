/**
 * 
 */
package data.struct.calc.stack;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import data.struct.calc.enums.OperatorEnum;

/**
 * @author YMZ
 *
 * ����enum�ķ�ʽ������ջ����,���и�λ���Ķ�����
 */

public class StackOperate {
    private static final boolean DEBUG = true;
/**
 * @author YMZ
 * methods getPexpr()
 * @return the String
 */
    public String getPexpr(String expr){
        String newexpr = expr.trim().replace(" ", "");
        if(DEBUG) System.out.println("newexpr:"+newexpr);
        char[] array = newexpr.toCharArray();
        StringBuffer sb = new StringBuffer();
        Stack<OperatorEnum> stack = new Stack<OperatorEnum>();
        OperatorEnum op;
        int len = array.length;
        int val = array[len-1];
        if(val != ')'){
            if (!(val >= '0' && val <= '9')){
                return "-1";    //�жϱ��ʽ�Ƿ�Ϸ�
            }
        }

        char[] newarray = switchArray(array);
        for(int i = 0; i < newarray.length; i++){
            Character ch = newarray[i];
            if(ch == ' '){
                continue;
            }
            if((op = isOperator(ch)) == null || ch == 'g' || ch == '.'){
                sb.append(ch);   //����ֱ�����
            }else{
                if(op.equals(OperatorEnum.RIGHT_BRACKET)){
                    while(!stack.empty() && stack.peek() != OperatorEnum.LEFT_BRACKET){
                        sb.append(stack.pop()); //��'()'�еı��ʽ���
                    }
                    stack.pop();    //����'('
                }else{
                    while(!stack.empty() && stack.peek().prior >= op.prior
                            && stack.peek() != OperatorEnum.LEFT_BRACKET){
                            sb.append(stack.pop()); //��ջ�����ȼ����ڵ�ǰ�����������
                    }
                    stack.push(op);    //������ջ
                }
            }
        }
        while(!stack.empty()){
            sb.append(stack.pop()); //���ջ��ʣ�������
        }
        if(DEBUG) System.out.println("sb:"+sb.toString());
        return sb.toString();
    }

/**
 * @author YMZ
 * methods getPexpr()
 * @return the String
 */
    public String getValue(String pexpr){
        if(pexpr.equals("-1")) return "expr is invaild";
        String value = "";
        StringBuffer sb = new StringBuffer();
        String newpexpr = pexpr.replace("gg", "g");
        char[] array = newpexpr.toCharArray();
        int len = array.length;
        Stack<Float> stack = new Stack<Float>();
        OperatorEnum op;
        for(int i = 0; i < len; i ++){
            Character ch = array[i];
            if((op = isOperator(ch)) != null){ //�ǲ�����
                stack.push(calc(op,stack.pop(),stack.pop()));
            }else{
                if(ch != 'g'){
                    sb.append(ch);
                }else{
                    if(DEBUG) System.out.println(Float.valueOf(sb.toString()));
                    stack.push(Float.valueOf(String.valueOf(sb.toString())));
                    sb.delete(0, sb.capacity());
                }
            }
        }
        if(!stack.empty()){
          value  = stack.peek().toString(); 
        }
        return value;
    }

/**
 * @author YMZ
 * methods calc()
 * @return the Float
 */
    private Float calc(OperatorEnum op,Float f1, Float f2){
        if(DEBUG) System.out.println("op.option:"+op.option+"; f1: "+f1+"; f2: "+f2);
        switch (op) {
        case PLUS:
            return f2 + f1;
        case MINUS:
            return f2 - f1;
        case MULTIPLY:
            return f2 * f1;
        case DIVIDE:
            if(f1 != 0){
                return f2 / f1;
            }
            return Float.valueOf(-0);
        case MODULAR:
            return f2 % f1;
        default:
            break;
        }
        return Float.valueOf(-0);
    }
/**
 * @author YMZ
 * methods isOperator()
 * @return the OperatorEnum
 */
    private OperatorEnum isOperator(Character ch){
        for(OperatorEnum op : OperatorEnum.values()){
            if(ch == op.option){
                return op;
            }
        }
        return null;
    }

/**
 * @author YMZ
 * methods lastisNumber()
 * �ж�array[]�������һ��Ԫ���Ƿ�Ϊ����
 * @return the boolean
 */
    private boolean lastisNumber(char[] array){
            if(array[array.length-1] >= '0' && array[array.length-1] <= '9'){
                return true;
            }
        return false;
    }

/**
 * @author YMZ
 * methods isOption()
 * unused
 * @return the String
 */
    private String isOption(char[] array){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < array.length-1; i++){
            if(array[i] >= '0' && array[i] <= '9'){
                if(!(array[i+1] >= '0' && array[i+1] <= '9')){
                    sb.append(i+1);
                    sb.append(" ");
                }
            }
        }
        if(DEBUG) System.out.println(sb.toString());
        return sb.toString();
    }

/**
 * @author YMZ
 * methods switchArray()
 * ����ת���������ֺ������ַ�֮�����'g'�������ָ��¼������
 * @return the char[]
 */
    private char[] switchArray(char[] array){
        ArrayList<Character> list = new ArrayList<>();
        int len = array.length;
        for(int i = 1; i < len; i ++){
            list.add(array[i-1]);
            if(array[i-1] >= '0' && array[i-1] <= '9'){
                if(!(array[i] >= '0' && array[i] <= '9') && array[i] != '.'){
                    list.add('g');
                }
            }
        }
        list.add(array[len-1]);
        if(lastisNumber(array)){
            list.add('g');
        }
        int j = 0;
        char[] newarray = new char[list.size()];
        for(Character l : list){
            newarray[j++] = l;
        }
        if(DEBUG) System.out.println(String.valueOf(list));
        if(DEBUG) System.out.println(String.valueOf(newarray));
        return newarray;
    }

/**
 * @author YMZ
 * methods switchArray()
 * ��������
 * @return the char[]
 */
    public String readScanner(){
        System.out.println("Please Input expr:");
        Scanner scanner = new Scanner(System.in);
        String expr = scanner.nextLine();
        scanner.close();
        return expr;
    }
}
