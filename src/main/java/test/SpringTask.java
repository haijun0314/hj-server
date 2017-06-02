package test;

import org.jxjz.common.util.ValidateUtils;
import org.springframework.stereotype.Service;
@Service 
public class SpringTask {
	    public void job1() { 
	        System.out.println(ValidateUtils.isNumeric("125698")); 
	    }  
}
