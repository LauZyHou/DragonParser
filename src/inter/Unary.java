package inter;

import lexer.Token;
import symbol.Type;

/**
 * 处理单目运算符
 */
public class Unary extends Op {
    public Expr expr;

    public Unary(Token tok, Expr x) {
        super(tok, null);
        expr = x;
        type = Type.max(Type.Int, expr.type);
        if (type == null)
            error("type error");
    }

    @Override
    public Expr gen() {
        return new Unary(op, expr.reduce());
    }

    @Override
    public String toString() {
        return op.toString() + " " + expr.toString();
    }
}
