/**
 * 
 */
package data.struct.calc;

import data.struct.calc.stack.StackOperate;

/**
 * @author YMZ
 *
 * @return the 
 */
public class Calc {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        StackOperate stack = new StackOperate();
        String expr = stack.readScanner();
        String result = stack.getValue(stack.getPexpr(expr));
        System.out.println("result: "+result);
    }

}
