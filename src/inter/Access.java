package inter;

import lexer.Tag;
import lexer.Word;
import symbol.Type;

/**
 * 111
 */
public class Access extends Op {
    public Id array;
    public Expr index;

    public Access(Id a, Expr i, Type p) {//p是将数组平坦化后的元素类型
        super(new Word("[]", Tag.INDEX), p);
        array = a;
        index = i;
    }

    @Override
    public Expr gen() {
        return new Access(array, index.reduce(), type);
    }

    @Override
    public void jumping(int t, int f) {
        emitjumps(reduce().toString(), t, f);
    }

    @Override
    public String toString() {
        return array.toString() + "[" + index.toString() + "]";
    }
}
