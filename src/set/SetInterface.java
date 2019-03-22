package set;

public interface SetInterface<Elem> {
    public int add(Elem elem);
    public void delete(Pos pos);
    public void delete(Key key);
    public Pos find(Key key);
    public Elem retrieve(Pos pos);
    public void showall();
    public int size();
    public SetInterface unify(Set s, Set t);
}