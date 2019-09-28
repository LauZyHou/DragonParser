package inter;

import lexer.Token;
import symbol.Type;

/**
 * 111
 */
public class Expr extends Node {
    public Token op;//运算符
    public Type type;//类型

    Expr(Token tok, Type p) {
        op = tok;
        type = p;
    }

    //返回可以做右值的项，这里是返回了表达式地址，子类可将其重写
    public Expr gen() {
        return this;
    }

    //将表达式规约成单一地址
    public Expr reduce() {
        return this;
    }

    //布尔表达式生成跳转代码
    public void jumping(int t, int f) {
        emitjumps(toString(), t, f);
    }

    public void emitjumps(String test, int t, int f) {
        if (t != 0 && f != 0) {
            emit("if " + test + " goto L" + t);
            emit("goto L" + f);
        }
        else if(t!=0)
            emit("if "+test+" goto L"+t);
        else if(f!=0)
            emit("iffalse "+test+" goto L"+f);
        else//t==0 and f==0
            ;
    }

    @Override
    public String toString() {
        return op.toString();
    }
}
