/**
 * 
 */
package data.struct.calc.enums;

/**
 * @author YMZ
 *
 * @return the
 */
public enum OperatorEnum {
    PLUS(0,'+'),
    MINUS(0,'-'),
    MULTIPLY(1,'*'),
    DIVIDE(1,'/'),
    MODULAR(1,'%'),
    LEFT_BRACKET(2,'('),
    RIGHT_BRACKET(2,')');

    public Integer prior; //���ȼ�
    public Character option; //������

    private OperatorEnum(Integer prior, Character option){
        this.prior = prior;
        this.option = option;
    }

    public String toString(){
        return String.valueOf(option);
    }
}
