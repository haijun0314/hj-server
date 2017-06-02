package org.jxjz.common.util;

public class PositionUtil {
	public static void main(String[] args) {

		
		System.out.print(distance(116.475846,39.977276, 116.442213,39.947965));
	
	}
	
	/**
	 * 计算两点距离
	 * @param n1
	 * @param e1
	 * @param n2
	 * @param e2
	 * @return
	 */
	public static double distance(double n1, double e1, double n2, double e2) {
		double jl_jd = 102834.74258026089786013677476285;// 每经度单位米;
		double jl_wd = 111712.69150641055729984301412873;// 每纬度单位米;
		double b = Math.abs((e1 - e2) * jl_jd);
		double a = Math.abs((n1 - n2) * jl_wd);
		return Math.sqrt((a * a + b * b));
	}
	
	  /**
     * 根据经纬度和距离返回一个矩形范围
     * @param lng经度
     * @param lat纬度
     * @param distance 距离(单位为米)
     * @return [lng1,lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */ 
    public static double[] getRectangle(double lng, double lat, long distance) { 
        float delta = 111000; 
        if (lng != 0 && lat != 0) { 
            double lng1 = lng - distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta); 
            double lng2 = lng + distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta); 
            double lat1 = lat - (distance / delta); 
            double lat2 = lat + (distance / delta); 
            return new double[] { lng1, lat1, lng2, lat2 }; 
        } else { 
            double lng1 = lng - distance / delta; 
            double lng2 = lng + distance / delta; 
            double lat1 = lat - (distance / delta); 
            double lat2 = lat + (distance / delta); 
            return new double[] { lng1, lat1, lng2, lat2 }; 
        } 
    } 
    /**
     * 根据经纬度和距离返回一个矩形范围
     * @param lng经度
     * @param lat纬度
     * @param distance 距离(单位为米)
     * @return [lng1,lat1, lng2,lat2] 矩形的左下角(lng1,lat1)和右上角(lng2,lat2)
     */ 
    public static String[] getRectangleStr(double lng, double lat, long distance) { 
        float delta = 111000; 
        if (lng != 0 && lat != 0) { 
            double lng1 = lng - distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta); 
            double lng2 = lng + distance / Math.abs(Math.cos(Math.toRadians(lat)) * delta); 
            double lat1 = lat - (distance / delta); 
            double lat2 = lat + (distance / delta); 
            return new String[] { lng1+"", lat1+"", lng2+"", lat2+"" }; 
        } else { 
            double lng1 = lng - distance / delta; 
            double lng2 = lng + distance / delta; 
            double lat1 = lat - (distance / delta); 
            double lat2 = lat + (distance / delta); 
            return new String[] { lng1+"", lat1+"", lng2+"", lat2+"" }; 
        } 
    } 
    /**
     * 得到两点间的距离 米
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     */ 
    public static double getDistanceOfMeter(double lat1, double lng1, double lat2, double lng2) { 
        double radLat1 = rad(lat1); 
        double radLat2 = rad(lat2); 
        double a = radLat1 - radLat2; 
        double b = rad(lng1) - rad(lng2); 
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) 
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2))); 
        s = s * EARTH_RADIUS; 
        s = Math.round(s * 10000) / 10; 
        return s; 
    } 
       
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    } 
   
    /**
     * 地球半径：6378.137KM
     */ 
    private static double EARTH_RADIUS = 6378.137;  	
	
	
	
	
	
	
	
	
	
}
