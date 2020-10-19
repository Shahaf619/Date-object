/**
 * This class represent a Library object.
 * @version (2018)
 * @author (course staff)
 */

public class Library
{
    private Book [ ] _lib ;
    private int _noOfBooks ;
    private final int MAX_BOOKS = 200 ;

    /**
     * Creates a new Library object, max amount of books to add is 200.
     */

    public Library ( )
    {
        _lib = new Book [ MAX_BOOKS ] ;
        _noOfBooks = 0 ;
    }

    /**
     * Add book on Library if the amount of books is less than 200.
     * @param b The book to add.
     * @return True if book added and false otherwise.
     */

    public boolean addBook ( Book b ) 
    {
        String studentName = b.getStudentName( ) ;
        Date borrowedDate = b.getBorrowedDate( ) ;
        boolean borrowStatus = b.getBorrowed ( ) ;

        if ( _noOfBooks < MAX_BOOKS )
        {
            for ( int i = 0 ; i < _lib.length ; i ++ )
            {
                if ( _lib [ i ] == null )
                {
                    _lib [ i ] = new Book ( b ) ;
                    _noOfBooks ++ ;
                    if ( borrowStatus == true )
                    {
                        _lib [ i ].borrowBook ( studentName , borrowedDate ) ;
                    }
                    return true ;
                }
            }
        }
        return false ;
    }

    /**
     * Remove all book's copies.
     * @param b The book to remove all of its copies on Library.
     */

    public void removeBook ( Book b )
    {
        if ( _noOfBooks > 0 )
        {
            for ( int i = 0 ; i < _noOfBooks ; i ++ )
            {
                if ( _lib [ i ].equals ( b ) )
                {
                    _lib [ i ] = null ;
                    _noOfBooks -- ;
                    if ( _lib [ _noOfBooks ] != null )
                    {
                        if ( _lib [ _noOfBooks ].equals ( b ) )
                        {
                            for ( int j = _noOfBooks ; _lib [ j ].equals ( b ) ; j -- )
                            {
                                _lib [ j ] = null ;
                                _noOfBooks -- ;
                                if ( _lib [ _noOfBooks ] == null ) 
                                {
                                    break ;
                                }
                            }
                            _lib [ i ] = _lib [ _noOfBooks ] ;
                            _lib [ _noOfBooks ] = null ;
                        }

                        else
                        {
                            _lib [ i ] = _lib [ _noOfBooks ] ;
                            _lib [ _noOfBooks ] = null ;
                        }
                    }
                }
            }
        }
    }

    /**
     * Calculate the amount of boroowed books.
     * @return The number of borrowed books.
     */

    public int howManyBooksBorrowed ( ) 
    {
        int borrowedBooks = 0 ;
        if ( _noOfBooks > 0 )
        {
            for ( int i = 0 ; i < _noOfBooks ; i ++ )
            {
                if ( _lib [ i ].getBorrowed ( ) == true )
                {
                    borrowedBooks ++ ;
                }
            }
        }
        return borrowedBooks ;
    }

    /**
     * Calculate how many books borrowed to student.
     * @param name The student's name.
     * @return The number of borrowed books to student.
     */

    public int howManyBooksBorrowedToStudent ( String name )
    {
        int numOfBooks = 0 ;

        for ( int i = 0 ; i < _noOfBooks ; i ++ )
        {
            if ( _lib [ i ].getBorrowed ( ) == true && _lib [ i ].getStudentName ( ).equals ( name ) )
            {
                numOfBooks ++ ;
            }
        }
        return numOfBooks ;
    }

    /**
     * Return which author has the largest amount of books on Library.
     * @return The author's name.
     */

    public String mostPopularAuthor ( )
    {
        if ( _noOfBooks > 0 )
        {
            int [ ] count = new int [ _lib.length ] ;
            int countSum = 1 ;
            int maxCount = 0 ;
            String popularAuthor = _lib [ 0 ].getAuthor ( ) ;

            for ( int i = 0 ; i < _noOfBooks ; i ++ )
            {
                count [ i ] = 0 ;

                for ( int j = 0 ; j < _noOfBooks ; j ++ )
                {
                    if ( _lib [ i ].getAuthor ( ).equals ( _lib [ j ].getAuthor ( ) ) && i != j )
                    {
                        countSum ++ ;
                    }
                }
                count [ i ] = countSum ;
                countSum = 1 ;

                if ( count [ i ] > maxCount )
                {
                    maxCount = count [ i ] ;
                    popularAuthor = _lib [ i ].getAuthor ( ) ;
                }
            }
            return popularAuthor ;
        }

        else
        {
            return null ;
        }
    }

    /**
     * Return the oldest book on Library.
     * @return The oldest book.
     */

    public Book oldestBook ( )
    {
        int minYear ;
        if ( _noOfBooks != 0 )
        {
            Book oldestBook = new Book ( _lib [ 0 ] ) ;
            minYear = _lib [ 0 ].getYear ( ) ;

            for ( int i = 0 ; i < _noOfBooks ; i ++ )
            {
                if ( _lib [ i ].getYear ( ) < minYear )
                {
                    minYear = _lib [ i ].getYear ( ) ;
                    oldestBook = new Book ( _lib [ i ] ) ;
                }
            }
            return oldestBook ;
        }

        else
        {
            return null ; 
        }
    }

    /**
     * Remove the first copy of book.
     * @param The title of book to remove.
     * @return The book that was removed.
     */

    public Book remove ( String name )
    {
        Book removedBook = new Book ( _lib [ 0 ] ) ; 

        for ( int i = 0 ; i < _noOfBooks ; i ++ )
        {
            if ( _lib [ i ].getTitle ( ).equals ( name ) )
            {
                removedBook = new Book ( _lib [ i ] ) ;
                if ( _lib [ _noOfBooks - 1 ].getBorrowed ( ) == true )
                {
                    _lib [ i ] = new Book ( _lib [ _noOfBooks - 1 ] ) ;
                    _lib [ i ].borrowBook ( _lib [ _noOfBooks - 1 ].getStudentName( ) , _lib [ _noOfBooks - 1 ].getBorrowedDate( ) ) ;
                }
                else
                {
                    _lib [ i ] = new Book ( _lib [ _noOfBooks - 1 ] ) ;
                }
                _noOfBooks -- ;
                _lib [ _noOfBooks ] = null ;
                return removedBook ;
            }
        }
        return null ;
    }

    /**
     * Return a string that represent this Library.
     * @return The string that represent this Library.
     */

    public String toString ( )
    {
        String headLine = "The books in the libary are:\n" ;
        String theString = headLine ;

        if ( _noOfBooks > 0 )
        {
            for ( int i = 0 ; i < _noOfBooks ; i ++ )
            {
                theString +=  _lib [ i ].toString ( ) + "\n"  ;
            }
        }
        return theString ;
    }
}
