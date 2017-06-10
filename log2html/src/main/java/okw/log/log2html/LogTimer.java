package okw.log.log2html;
 
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
 
 
public class LogTimer {
 
        private long TimerBegin;
        private long TimerEnd;

        private Instant lvStartTime;
        private Instant lvEndTime;
         
        public void startTimer()
        {
                TimerBegin = System.nanoTime();
                lvStartTime = Instant.now();
        }
       
        public void stopTimer()
        {
                TimerEnd = System.nanoTime();
                lvEndTime = Instant.now();
        }
       
        public long getNanos()
        {
                long lvlReturn = TimerEnd - TimerBegin;
               
                return lvlReturn;              
        }
 
        public long getMillis()
        {
                long lvlReturn = TimerEnd - TimerBegin;
 
                lvlReturn = TimeUnit.MILLISECONDS.convert(lvlReturn, TimeUnit.NANOSECONDS);
               
                return lvlReturn;
        }
 
        public String getSeconds(String  fpsFormat)
        {
                final double seconds = ((double)getNanos() / 1000000000);
 
                DecimalFormat myFormatter = new DecimalFormat(fpsFormat);
                String output = myFormatter.format(seconds);
               
                return output;
        }

        
        public String getMilliSeconds(String fpsFormat)
        {
                final double seconds = ((double)getNanos() / 1000000);
 
                DecimalFormat myFormatter = new DecimalFormat(fpsFormat);
                String output = myFormatter.format(seconds);
               
                return output;
        }

        
        public String getStartTime()
        {
        	String lvsReturn = lvStartTime.toString();
        	return lvsReturn;
        }

        
        public String getEndTime()
        {
        	String lvsReturn = lvEndTime.toString();
        	return lvsReturn;
        }

}
 