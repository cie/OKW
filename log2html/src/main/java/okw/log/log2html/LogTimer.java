package okw.log.log2html;
 
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
 
 
public class LogTimer {
 
        long TimerBegin;
        long TimerEnd;
 
        public void StartTimer()
        {
                TimerBegin = System.nanoTime();
        }
       
        public void StopTimer()
        {
                TimerEnd = System.nanoTime();
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
}
 