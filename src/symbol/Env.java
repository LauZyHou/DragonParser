package symbol;

import lexer.Token;

import java.util.Hashtable;

/**
 * 111
 */
public class Env {
    private Hashtable table;
    protected Env prev;//上一个

    public Env(Env n) {
        table = new Hashtable();
        prev = n;
    }

    public void put(Token w, Id i) {
        table.put(w, i);
    }

    public Id get(Token w) {
        for(Env e=this;e!=null;e=e.prev) {
            Id found = (Id)e.table.get(w);
            if(found!=null)
                return found;
        }
        return null;
    }
}
