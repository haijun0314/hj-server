package org.jxjz.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil{
	public TimeUtil() {
    }
    public static String getCurTimeString(){
        Date D = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(D);
        return date;
    }
    
    public static String  getEndDateStr(String endTime){
		endTime=endTime+" 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = sdf.parse(endTime);
			endTime=sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		 return endTime;
    }    
    
    public static String  getEndDate(String endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date;
		try {
			date = sdf.parse(endTime);
			endTime=sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		 return endTime;
    }    
    public static String  getStartDate(String startTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date;
		try {
			date = sdf.parse(startTime);
			startTime=sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		 return startTime;
    }    
    public static Date getCurDate(){
        Date D = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.format(D);
        return D;
    }
    public static String ParseDateToTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }
    public static String ParseDateToSimTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        return time;
    }
    /*锟斤拷锟斤拷锟斤拷锟斤拷锟绞憋拷址锟�/
    public static String timeYMDHStr(){
    	 Date D = new Date();
         SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHH");
         String date = sf.format(D);
         return date;
    }
    /*锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷址锟�/
    public static String timeYMDStr(){
   	 Date D = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String date = sf.format(D);
        return date;
   }
    public static String getWenHouYu(){
        String returnstring="";
        SimpleDateFormat setDateFormat = new SimpleDateFormat("HH");
        String temp = setDateFormat.format(new java.util.Date());
        int ii = Integer.parseInt(temp);
        if (ii > 6 && ii < 8 || ii == 8) {
            returnstring="锟斤拷锟较猴拷! ";
        } else if (ii > 8 && ii < 11) {
            returnstring="锟斤拷锟斤拷锟� ";
        } else if ((ii > 11 && ii < 13) || ii == 11 || ii == 13) {
            returnstring="锟斤拷锟斤拷锟� ";
        } else if (ii > 13 && ii < 18) {
            returnstring="锟斤拷锟斤拷锟� ";
        } else if ((ii > 18 && ii < 22) || ii == 18) {
            returnstring="锟斤拷锟较猴拷! ";
        } else {
            returnstring="锟斤拷锟斤拷锟斤拷锟斤拷夜锟斤拷锟斤拷注锟斤拷锟斤拷息锟斤拷锟斤拷";
        }
        return returnstring;
    }
    /**
     * 锟斤拷取时锟斤拷锟�
     * @return
     */
    
    public static Date getDateYMDHMS(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date == null || "".equals(date)) {
			return null;
		}
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}	
	public static Date getDateYMD(String date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (date == null || "".equals(date)) {
			return null;
		}
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public static Date getDateYMDHMS1(String date) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		if (date == null || "".equals(date)) {
			return null;
		}
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	
	 

	 

	public static String formatDateYMDHMS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		return time;
	}

	public static String formatDateYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		return time;
	}

	public static String getDateYMDH() {
		Date D = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHH");
		String date = sf.format(D);
		return date;
	}

	public static String getDateYMD() {
		Date D = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String date = sf.format(D);
		return date;
	}
	public static String getMonth() {
		Date D = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
		String date = sf.format(D);
		return date;
	}

	public static String getDateYMDHMS() {
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(c.getTime());
	}

	/**
	 * 获取中文时间
	 */
	public static String getCHDate(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
		String ret = sf.format(date);
		return ret;
	}
	 

	/**
	 * 获取时间差
	 */
	public static String getTimeDistance(Date time1, Date time2) {
		long l = time1.getTime() - time2.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String result = "" + day + "天" + hour + "小时ʱ" + min + "分钟" + s + "秒";
		return result;
	}
	
	/**
	 * 获取时间差   天数
	 */
	public static int getBetweenDays(String t1, String t2)
			throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int betweenDays = 0;
		Date d1 = format.parse(t1);
		Date d2 = format.parse(t2);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR)
				- c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}
		return betweenDays;
	}
	/**
	 * 获取某一月
	 */
	public static Date getSpecialMonth( int month){
		try {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.MONTH,month);
			return c1.getTime();
		} catch (Exception e) {
			 return  null;
		}

	}
	/**
	 * 获取某一天
	 */
	public static String getSpecialDate( int date){
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DATE,date);
			return format.format(c1.getTime());
		} catch (Exception e) {
			 return  null;
		}
	}	
	/**
	 * 获取某一天
	 */
	public static String getOneDay( int date){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DATE,date);
			return format.format(c1.getTime());
		} catch (Exception e) {
			 return  null;
		}
	}	
	/**
	 * 获取某一天
	 */
	public static String getDate( int date,String startDay){
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		DateFormat format2 = new SimpleDateFormat("MMdd");
		try {
			Date d = format.parse(startDay);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(d);
			c1.add(Calendar.DATE,date);
			return format2.format(c1.getTime());
		} catch (Exception e) {
			 return  null;
		}
	}	
	
	/**
	 * 获取某一天
	 */
	public static Date getSpecialHour( int hour){
		try {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.HOUR,hour);
			return c1.getTime();
		} catch (Exception e) {
			 return  null;
		}
	}	
	
	/**
	 * 获取某一天
	 */
	public static Date getSpecialMinute( int minute){
		try {
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.MINUTE,minute);
			return c1.getTime();
		} catch (Exception e) {
			 return  null;
		}
	}	
	  /** 
     * 得到本月的第一天 
     */ 
    public static String getMonthFirstDay() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.DAY_OF_MONTH, calendar  
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat simpleFormate = new SimpleDateFormat("yyyyMMdd");
        return simpleFormate.format(calendar.getTime());  
    }  

	/** 
   * 得到本月的天数 
   */ 
    public static int getdays()
    {
    	java.util.Calendar cal = java.util.Calendar.getInstance();
    	int maxDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		return maxDay; 
    	
    }
    
    /** 
     * 得到本周的第一天 
     */ 
    public static String getWeekDay() {
//    	  Map<String,String> map = new HashMap<String,String>();
    	  Calendar cal =Calendar.getInstance();
    	        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
    	        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); //获取本周一的日期
//    	        map.put("mon", df.format(cal.getTime()));
//    	  print("********得到本周一的日期*******"+df.format(cal.getTime()));
    	  //这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
//    	  cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	  //增加一个星期，才是我们中国人理解的本周日的日期
//    	  cal.add(Calendar.WEEK_OF_YEAR, 1);
//    	  map.put("sun", df.format(cal.getTime()));
//    	  print("********得到本周天的日期*******"+df.format(cal.getTime()));
    	  return df.format(cal.getTime());
    	 }
    
    
    
    public static  void main(String args[]){
//    	Calendar c1 = Calendar.getInstance();
//    	int dat2=   Calendar.DATE;
//   	    int day=c1.get(Calendar.DATE);
//    	String startDay="20141024";
//    	DateFormat format = new SimpleDateFormat("yyyyMMdd");
//    	DateFormat format2 = new SimpleDateFormat("MMdd");
//		try {
//			Date d = format.parse(startDay);
//			Calendar c1 = Calendar.getInstance();
//			c1.setTime(d);
//			c1.add(Calendar.DATE,1);
//			format2.format(c1.getTime());
//			System.out.print(format2.format(c1.getTime()));
//			//return format.format(c1.getTime());
//		} catch (Exception e) {
//			// return  null;
//		}
    	System.out.println(getDateYMDHMS1("20140505"));
   }
}



