/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Brandon Magistrado
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    public LogAnalyzer(String name)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(name);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * gives the number of entires
     * @return total which is the number of accesses
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int I: hourCounts)
        {
            total += I;
        }
        
        return total;
    }
    
    /**
     * gives the busiest hour
     * @return returns the hour of the largest number of acceses
     * gives the first hour if multiple hours are the same
     */
    public int busiestHour()
    {
        int busyHour = 0;
        int biggestNum = Integer.MIN_VALUE;
        
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] > biggestNum)
            {
                biggestNum = hourCounts[i];
                busyHour = i;
            }
        }
        return busyHour;
    }
    
    /**
     * gives the quietest hour
     * @return returns the hour of the smallest number of acceses
     * gives the first hour if multiple hours are the same
     */
    public int quietestHour()
    {
        int quietHour = 0;
        int smallestNum = Integer.MAX_VALUE;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] < smallestNum)
            {
                smallestNum = hourCounts[i];
                quietHour = i;
            }
        }
        return quietHour;
    }
    
    /**
     * gives the first hour of the subsequent busiest two hours
     * @return returns the first hour of the two adjacent busiest
     */
    public int busiestTwoHour()
    {
        int busyTwoFirstHour = 0;
        int biggestTwo = Integer.MIN_VALUE;
        
        for(int i = 0; i < hourCounts.length - 1; i++)
        {
            if((hourCounts[i] + hourCounts[i+1]) > biggestTwo)
            {
                biggestTwo = (hourCounts[i] + hourCounts[i + 1]);
                busyTwoFirstHour = i;
                System.out.println(biggestTwo);
            }
        }
        return busyTwoFirstHour;
    }
    
    
}
