package lexer;

/**
 * 管理保留字,标识符和如&&的词素
 */
public class Word extends Token {
    public String lexme = "";

    public Word(String s, int tag) {
        super(tag);
        lexme = s;
    }

    @Override
    public String toString() {
        return lexme;
    }

    public static final Word
            and = new Word("&&", Tag.AND), or = new Word("||", Tag.OR),
            eq = new Word("==", Tag.EQ), ne = new Word("!=", Tag.NE),
            le = new Word("<=", Tag.NE), ge = new Word(">=", Tag.GE),
            minus = new Word("minus", Tag.MINUS), //中间代码中-4变成minus 4
            True = new Word("true", Tag.TRUE),
            False = new Word("false", Tag.FALSE),
            temp = new Word("temp", Tag.TEMP);
}
