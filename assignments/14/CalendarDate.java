import java.util.Comparator;

// The CalendarDate class stores information about a single calendar date
// (upgraded from BJP text Chapter 10)
// 
// Posted on CS211 CANVAS site for Bellevue College course
// W.P. Iverson, February 2017
// similar code to www.buildingjavaprograms.com supplements
// added Comparator and year

public class CalendarDate implements Comparable<CalendarDate>, Comparator<CalendarDate> {
	// FIELDS
    private int month;
    private int day;
    private int year;

    // Constructors
    public CalendarDate() {
    	// default 0,0 makes no sense, so using 1,1
    	this(1,1,1970); // zero epoch UNIX
    }
    
    public CalendarDate(int month, int day, int year) {
    	if (month<1 || month>12 || day<1 || day>31 || year<5 || year>9999)
    		throw new IllegalArgumentException("Invalid month/day/year");
        this.month = month;
        this.day = day;
        this.year = year;
    }

    // ACCESSORS (getters)
    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
    
    public int getYear() {
    	return year;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }
    
    public String longDate() {
    	String[] names = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    	return names[month-1] + " " + day + ", " + year;
    }

    // Compares this calendar date to another date.
    // Dates are compared by month and then by day.
    public int compareTo(CalendarDate other) {
        if (this.year != other.year) {
        	return this.year - other.year;
        } else if (this.month != other.month) {
            return this.month - other.month;
        } else {
            return this.day - other.day;
        }
    }    
    
    // for Comparator
	public int compare(CalendarDate first, CalendarDate second) {
		// Should be the same as compareTo() result
		return first.compareTo(second);
	}
	
	public boolean equals(CalendarDate other) {
		return (this.compareTo(other)==0);
	}
}
