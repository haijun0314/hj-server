package org.jxjz.common.util;


public class ArrayUtil {

    /***************************************************************************************  
     * �ж��ַ��Ƿ�����������
     ****************************************************************************************/
	public static boolean checkExistStrArray(String str,String[] fields){
		if(fields==null||fields.length==0)return false;
		for (int j=0;j<fields.length;j++) {
			String field=fields[j];
			if(str.equals(field)){
				return true;
			}
		}
		return false;
	}
}
