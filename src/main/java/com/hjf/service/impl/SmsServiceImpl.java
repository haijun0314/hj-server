package com.hjf.service.impl;

import org.jxjz.common.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjf.core.util.SmsUtils;
import com.hjf.service.SmsService;
@Service
@Transactional 
public class SmsServiceImpl extends BaseService implements SmsService {
//	@Resource SmsRegDAO smsRegDAO;
	/**
	 * 注册短信验证码
	 */
	public  String  sendValidateSms(String telePhone) {
		log.info("发送短信...telePhone"+telePhone);
		String smsCode=StringUtil.randNumStr(4);
		boolean flag=SmsUtils.sendValidateSms(telePhone,smsCode);
		if (!flag) {
			log.info("发送短信...发送失败  telePhone="+telePhone+"ret="+flag);
			return "";	
		}
		log.info("发送短信...发送成功  telePhone"+telePhone);
//		int ret=smsRegDAO.deleteByID(telePhone, "SmsReg.delete");
//		if (ret>-1) {
//			SmsReg  sms=new SmsReg();
//			sms.setTelephone(telePhone);
//			sms.setCode(smsCode);
//			smsRegDAO.save(sms);
//		}
		return smsCode;	
	}
		
	
	/**
	 * 验证码验证
	 */
	public  boolean  smsCheck(String telePhone,String smsCode) {
//		Query query= new Query();
//		query.append("code", smsCode);
//		query.append("telephone", telePhone);
//		List list=smsRegDAO.queryList(query);
//		if (list==null||list.size()<1) {
//			return false;
//		}
//		SmsReg sms=(SmsReg)list.get(0);
//		Date date=TimeUtil.getSpecialMinute(-10);
//		if (date.after(sms.getCreatedTime())) {
//			return false;	
//		}
		return true;	
	}	
	 
	 
	
}
