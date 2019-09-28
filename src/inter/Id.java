package inter;

import lexer.Word;
import symbol.Type;

/**
 * 一个标识符就是一个i地址
 */
public class Id extends Expr{
    public int offset;//相对地址
    public Id(Word id, Type p,int b){
        super(id,p);
        offset = b;
    }
}
