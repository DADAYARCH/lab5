package test;

import structures.Worker;

import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

public class MainTest {
    public static void main(String[] args){
        Hashtable<String, Worker> map = new Hashtable<>();

        Date date1 = new Date(2005, 12,17);
        Date date2 = new Date(1905, 1,3);

        for (int i=10; i > 0; i = i - 1){
            Worker worker = new Worker();
            if(i%2==0){
                worker.setStartDate(date1);
                map.put(Integer.toString(i),worker);
            }else{
                worker.setStartDate(date2);
                map.put(Integer.toString(i),worker);
            }
        }
        String regex = "Jan 17 00:00:00 MSK 3906";
        Set<String> setOfKeys = map.keySet();
       /* for (String key: setOfKeys){
            if (map.get(key) == regex)*/
        }



    //System.out.println(map);


}
