import java.util.Vector;

public interface Aggregate{
    //数え上げる対象となる集合体のクラス内にイテレータを設ける
    public abstract Iterator();
}

public interface Iterator{
    //hasNext():配列の最後かどうか next():現在の要素を返しポインタを進める
    public abstract boolean hasNext();
    public abstract Object next();
}

class Book{
    private String name;
    private String author;

    public Book(String name, String author){
        this.name=name;
        this.author=author;
    }

    public String getName(){
        return this.name;
    }

    public String getAuthor(){
        return this.author;
    }
}

class BookShelf implements Aggregate{
    private Vector<Book> books;

    public void append(Book book){
        this.books.add(book);
    };

    public BookShelf(){
        this.books=new Vector<Book>();
    }

    public Book getBookAt(int index){
        return books.elementAt(index);
    }

    public int getLength() {
        int size = this.books.size();
        return size;
    }

    //インターフェース:Aggregateの実装
    public Iterator iterator(){
        return new BookShelfIterator(this);
    }
}

class BookShelfIterator implements Iterator{
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        } else {
            return false;
        }
    }

    public Object next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();

        bookShelf.append(new Book("diary", "spkn"));
        bookShelf.append(new Book("works", "spcn"));
        bookShelf.append(new Book("test book", "sc"));
        bookShelf.append(new Book("Iterator pattern", "gof"));

        //イテレータによる数え上げ処理
        Iterator iterator = bookShelf.iterator();
        while(iterator.hasNext()) {
            Book book = (Book)iterator.next();
            System.out.println("-"+book.getName()+": "+book.getAuthor());
        }
    }
}
