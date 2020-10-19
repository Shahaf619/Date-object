/**
 * This class represent a Book object.
 * @version (2018)
 * @author (course staff)
 */

public class Book
{
    private String _title ;
    private String _author ;
    private int _yearPublished ;
    private int _noOfPages ;
    private boolean _borrowed ;
    private String _studentName ;
    private Date _borrowedDate ;
    private Date _returnDate ;
    
    private final int MAX_DAYS = 30 ;
    private final int OVER_DAYS = 60 ;
    private final int PENALTY = 5 ;
    private final int MIN_YEAR = 1800 ;
    private final int MAX_YEAR = 2018 ;
    private final int DEFAULT_YEAR = 2000 ;
    private final int DEFAULT_PAGE = 1 ;
    private final int DEFAULT_NUM = 0 ;

    /**
     * Creates a new Book object. 
     * If the published date and the number of pages is invalid, then it will be initialized to default values, for year: 2000 and for: 1.
     * @param title The name of the book
     * @param author The name of the author
     * @param year The published year of the book
     * @param pages The number of pages in a the book
     */

    public Book ( String title , String author , int year , int num )
    {
        _title = new String ( title ) ;
        _author = new String ( author ) ;
        _yearPublished = year ;
        _borrowed = false ;
        _studentName = null ;
        _borrowedDate = null ;
        _returnDate = null ;

        if ( year >= MIN_YEAR && year <= MAX_YEAR )
            _yearPublished = year ;

        else
            _yearPublished = DEFAULT_YEAR ;

        if ( num > 0 )  
            _noOfPages = num ;

        else
            _noOfPages = DEFAULT_PAGE ;
    }

    /**
     * Copy constructor.
     * @param other The book to be copied
     */

    public Book ( Book other )
    {
        _title = new String ( other._title ) ;
        _author = new String ( other._author ) ;
        _yearPublished = other._yearPublished ;
        _borrowed = false ;
        _studentName = ( _studentName == null ) ? null : other._studentName ;
        _borrowedDate = ( _borrowedDate == null ) ? null : other._borrowedDate ;
        _returnDate = ( _returnDate == null ) ? null : other._returnDate ;
        _noOfPages = other._noOfPages ;
    }

    /**
     * Gets the name of the book.
     * @return the title
     */

    public String getTitle ( )
    {
        return _title ;
    }

    /**
     * Gets the author's name.
     * @return the author
     */

    public String getAuthor ( )
    {
        return _author ;
    }

    /**
     * Gets the published year of the book.
     * @return the year
     */

    public int getYear ( )
    {
        return _yearPublished ;
    }

    /**
     * Gets the number of pages in a book.
     * @return the pages
     */

    public int getPages ( )
    {
        return _noOfPages ;
    }

    /**
     * Gets if the book is borrowed.
     * @return the borrowing status
     */

    public boolean getBorrowed ( )
    {
        return _borrowed ;
    }

    /**
     * Gets the student's name.
     * @return the name of a student
     */

    public String getStudentName( ) 
    {
        if ( _studentName == null )
             return null ;
        else     
             return _studentName ;
    }

    /**
     * Gets the book's return date.
     * @return the return date
     */

    public Date getReturnDate ( )
    {
        if ( _returnDate == null )
             return null;
             
        else     
             return new Date ( _returnDate ) ;
    }

    /**
     * Gets the book's borrowed date.
     * @return the borrowed date
     */

    public Date getBorrowedDate( )
    {
        if ( _borrowedDate == null )
             return null ;
             
        else     
             return new Date ( _borrowedDate ) ;
    }

    /**
     * Sets the book's title.
     * @param s The book's title to be set
     */

    public void setTitle ( String s )
    {
        _title = s ;
    }

    /**
     * Sets the author's name.
     * @param s The author's name to be set
     */

    public void setAuthor ( String s )
    {
        _author = s ;
    }

    /**
     * Sets the published year of the book (only if year is between 1800-2018).
     * @param n The published year to be set
     */

    public void setYear ( int n )
    {
        if ( n >= MIN_YEAR && n <= MAX_YEAR )
            _yearPublished = n ;
    }

    /**
     * Sets the number of pages in a book (only if number of pages is a positive number).
     * @param n The number of pages to be set
     */

    public void setPages ( int n )
    {
        if ( n >=0 )
            _noOfPages = n ;
    }

    /**
     * Returns a String that represents this book.
     * @return String that represents this book.
     */

    public String toString ( )
    {
        return "Title: " +  _title + "\t" + "Author: " + _author + "\t" + "Year: " + _yearPublished + ", " + _noOfPages + " pages" ;
    }

    /**
     * Check if 2 titles of books are the same.
     * @param other The book to be compare to
     * @return true if titles are the same
     */

    public boolean equals ( Book other )
    {
        return ( _title.equals ( other._title ) && _author.equals ( other._author ) && _yearPublished == other._yearPublished && _noOfPages == other._noOfPages ) ;
    }

    /**
     * Check if this book is older than other book.
     * @param other The book to be compare to
     * @return true if this book's published year is older than other book
     */

    public boolean olderBook ( Book other )
    {
        return ( other._yearPublished > this._yearPublished ) ;

    }

    /**
     * Check if 2 authors names of book are the same.
     * @param other The author's book to be compare to
     * @return true if 2 authors names of book are the same
     */

    public boolean sameAuthor ( Book other )
    {
        return ( _author.equals ( other._author ) ) ;
    }

    /**
     * Sets student name and borrow date. 
     * @param name The student's name
     * @param d The date book is borrowed
     */

    public void borrowBook ( String name , Date d )
    {
        if ( this._borrowed != true )
        {
             _studentName = name ;
             _borrowedDate = new Date ( d ) ;
             _borrowed = true ;
            }
    }

    /**
     * Sets return date of book.
     * @param d The date book was returned
     * @return true if days borrowed is over 30 days and false otherwise
     */

    public boolean returnBook ( Date d )
    {
        if ( _borrowed == true )
        {
            _returnDate = new Date ( d ) ;
            int dayBorrowed = _returnDate.difference ( _borrowedDate ) ;
            _borrowed = false ; 
            _studentName = null ;
            return ( dayBorrowed > MAX_DAYS ) ;
        }
        
        else
            return true ;
    }

    /**
     * Calculate how many days book is borrowed.
     * @param d Today's date to be calculated
     * @return The number of days book is borrowed
     */

    public int howLongBorrowed ( Date d )
    {
        if ( _borrowed != true )
            return DEFAULT_NUM ;
            
        else    
        {
        int daysBorrow = _borrowedDate.difference ( d ) ;    
        
        if ( _borrowedDate.after ( d ) )
             return DEFAULT_NUM ;

        else
            return daysBorrow ;
        }
    }

    /**
     * Check if book is available to be borrowed.
     * @param d The date to check if book is available
     * @return True if book is available to be borrow and false otherwise
     */

    public boolean  isAvailable ( Date d )
    {
        int dayInWeek = d.dayInWeek ( ) ;

        if ( _borrowed == true )
            return false ;

        else
        if ( ( dayInWeek == 0 ) || ( dayInWeek == 6 ) )
            return false ;

        else
            return true ;
    } 

    /**
     * Calculate how much student need to pay penalty.
     * @param d The date to be calculated
     * @return The payment penalty 
     */
    public int computePenalty ( Date d )
    {
        int daysBorrow = this.howLongBorrowed ( d ) ;
        int needToPay ;
        int dayLate = daysBorrow % MAX_DAYS ;
        int overLate = daysBorrow % OVER_DAYS ;
        final int DEFAULT = 2 ;

        if ( daysBorrow <= MAX_DAYS )
        {
            return needToPay = DEFAULT_NUM ;
        }

        else {

             if ( daysBorrow > MAX_DAYS && daysBorrow < OVER_DAYS )
             {
             needToPay =  dayLate * PENALTY ;
             return needToPay ;
             }

             else
             
             {
               needToPay = ( ( daysBorrow - OVER_DAYS ) * ( PENALTY * DEFAULT ) + ( MAX_DAYS * PENALTY ) ) ;
               return needToPay ;
            }
        }
    }
}


            
    


