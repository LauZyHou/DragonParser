package inter;

import lexer.Token;
import symbol.Type;

import java.time.temporal.Temporal;

/**
 * reduce的一个实现
 */
public class Op extends Expr {
    public Op(Token tok, Type p) {
        super(tok, p);
    }

    @Override
    public Expr reduce() {
        Expr x = gen();
        Temp t = new Temp(type);
        emit(t.toString() + " = " + x.toString());
        return t;
    }
}
