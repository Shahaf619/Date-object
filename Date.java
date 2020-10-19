
/**
 * This class represents a Date object.
 * @version (2018)
 * @author (Shahaf619)
 */

public class Date
{
    private int _day ;
    private int _month ;
    private int _year ;
    private final int MIN_DAY = 1 ;
    private final int MIN_MONTH = 1 ;
    private final int MAX_MONTH = 12 ;
    private final int MIN_YEAR = 1000 ;
    private final int MAX_YEAR = 9999 ;

    /**
     * Creates a new Date object if the date is valid, otherwise creates the date 1/1/2000.
     * @param day The day in the month (1-31)
     * @param month The month in the year (1-12)
     * @param The year (4 digits)
     */

    public Date ( int day , int month , int year )
    {
        final int DEFAULT_DAY = 1 ;  
        final int DEFAULT_MONTH = 1 ;
        final int DEFAULT_YEAR = 2000 ;
        int maxDays = this.dayCheck ( month , year ) ;

        if ( ( month >= MIN_MONTH &&  month <= MAX_MONTH ) && ( ( day >= MIN_DAY && day <= maxDays ) ) )       
        {
            _month = month ;
        }

        else
        {
            _day = DEFAULT_DAY ;
            _month = DEFAULT_MONTH ;
            _year = DEFAULT_YEAR ;
        }

        if ( day >= MIN_DAY && day <= maxDays )
        {
            _day = day ;
        }

        else
        {
            _day = DEFAULT_DAY ;
            _month = DEFAULT_MONTH ;
            _year = DEFAULT_YEAR ;
        }

        if ( ( year >= MIN_YEAR && year <= MAX_YEAR ) && ( day >=1 && day <= maxDays ) )

        {
            _year = year ;
        }

        else
        {
            _day = DEFAULT_DAY ;
            _month = DEFAULT_MONTH ;
            _year = DEFAULT_YEAR ;
        }

    }

    /**
     * Copy constructor.
     * @param other The date to be copied
     */

    public Date ( Date other )
    {
        _day = other._day ;
        _month = other._month ;
        _year = other._year ;
    }

    /**
     * Gets the Day.
     * @return the day
     */

    public int getDay ( )
    {
        return _day ;
    }

    /**
     * Gets the month.
     * @return the month
     */

    public int getMonth ( )
    {
        return _month ;
    }

    /**
     * Gets the year.
     * @return the year
     */

    public int getYear ( )
    {
        return _year ;
    }

    /**
     * Sets the day (only if date remains valid).
     * @param dayToSet The day value to be set
     */

    public void setDay ( int dayToSet )
    {
        int month = this._month ;
        int year = this._year ;
        int maxDays = this.dayCheck ( month , year ) ;
        if ( dayToSet >= MIN_DAY && dayToSet <= maxDays )
            _day = dayToSet ;
    }

    /**
     * Set the month (only if date remains valid).
     * @param monthToSet The month value to be set
     */    

    public void setMonth ( int monthToSet )
    {
        int day = this._day ;
        int year = this._year ;
        int maxDays ;
        if ( monthToSet >= MIN_MONTH &&  monthToSet <= MAX_MONTH )
        {
            {

                maxDays = this.dayCheck ( monthToSet , year ) ;
            }

            if ( day >= MIN_DAY && day <= maxDays )
            {
                _month = monthToSet ;
            }
        }
    }

    /**
     * Sets the year (only if date remains valid).
     * @param yearToSet The year value to be set
     */    

    public void setYear ( int yearToSet )
    {
        int day = this._day ;
        int month = this._month ;
        int maxDays ;
        if ( yearToSet >= MIN_YEAR && yearToSet <= MAX_YEAR )
        {
            {
                maxDays = this.dayCheck ( month , yearToSet ) ;
            }

            if ( day >= MIN_DAY && day <= maxDays )
            {
                _year = yearToSet ;
            }
        }
    }

        /**
         * Check if 2 dates are the same.
         * @param other The date to compare this date to
         * @return true if the dates are the same
         */    

        public boolean equals ( Date other )
        {
        return ( other._day == _day && other._month == _month && other._year == _year ) ;
    }

    /**
     * Check if this date is before other date.
     * @return true if this date is before other date
     */

    public boolean before ( Date other )
    {
        return ( (other._year > _year ) || ( other._year == _year &&  other._month > _month ) || ( other._year == _year && other._month == _month && other._day > _day ) ) ;
    }

    /**
     * Check if this date is after other date.
     * @return true if this date is after other date
     */

    public boolean after ( Date other )
    {
        return other.before ( this ) ;
    }

    /**
     * Calculates the difference in days between two dates.
     * @param other The date to calculate the difference between
     * @return the number of days between the dates
     */

    public int difference ( Date other )
    {
        return Math.abs ( this.calculateDate ( _day , _month , _year ) - other.calculateDate ( other._day , other._month , other._year ) ) ;
    }

    /**
     * Returns a String that represents this date.
     * @return String that represents this date in the following format: day/month/year for example: 2/3/1998
     */

    public String toString ( )     
    {
        return ( _day + "/" + _month + "/" + _year ) ;
    }

    /**
     * Calculate the day of the week that this date occurs on 0-Saturday 1-Sunday 2-Monday etc.
     * @return the day of the week that this date occurs on
     */

    public int dayInWeek  ( )
    {
        if ( this.whichDay ( ) < 0 )
            return Math.floorMod ( this.whichDay ( ) , 7 ) ;

        else    
            return this.whichDay ( ) ;
    } 

    // ---------------------------------------------------------------
    // This method calculate the number of days for a given month.
    // ---------------------------------------------------------------

    private int dayCheck ( int month , int year )
    { 
        int numDays = 0 ;

        switch ( month )
        {
            case 2 : if ( this.leap ( year ) ) // February
                numDays = 29 ;  

            else
                numDays = 28 ;                               
            break ;  

            case 1 :                          // January
            case 3 :                          // March          
            case 5 :                          // May           
            case 7 :                          // July         
            case 8 :                          // August            
            case 10 :                         // October              
            case 12 : numDays = 31 ;          // December                                     
            break ;                        

            case 4 :                          // April            
            case 6 :                          // June             
            case 9 :                          // September          
            case 11 : numDays = 30 ;          // November                          
            break ;
        }
        return numDays ;   
    }

    // ------------------------------------------------------------------------------------------------
    // This method calculate the number of days from the beggining of the counting to a given date.
    // ------------------------------------------------------------------------------------------------

    private int calculateDate ( int day , int month , int year ) 
    {
        if ( month < 3 )
        {
            year -- ;
            month = month + 12 ;
        }

        return 365 * year + year / 4 - year / 100 + year / 400 + ( ( month+1 ) * 306 ) / 10 + ( day - 62 ) ;
    }

    // --------------------------------------------------------------
    // This method calculate the day of a week for a given date.
    // --------------------------------------------------------------

    private int whichDay ( )
    {
        final int D , M , Y , C ;
        int Day ;
        D = _day ;

        if ( _month > 2 )
            M = _month ;

        else
            M = _month + 12 ;

        if ( _month > 2 )    
            Y = _year % 100 ;

        else
            Y = ( _year % 100 ) - 1 ;

        C = _year / 100 ;

        Day = ( D + ( 26 * ( M + 1 ) ) / 10 + Y + Y / 4 + C / 4 - 2 * C ) % 7 ;

        return Day ;
    }    

    // ------------------------------------------------------
    // This method check if a given year is a leap year.
    // ------------------------------------------------------

    private boolean leap ( int y )
    {
        return ( y % 4 == 0 && y % 100 != 0 ) || ( y % 400 == 0 ) ;
    }
}


