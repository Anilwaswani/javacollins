package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataValidator {
public static boolean isNull(String data){
	if( data== null || data.trim().length()==0){
	return true;
	}else{
		return false;
	}
	}
public static boolean hasWhiteSpace(String data){
	if (data.contains(" ")){
		return true;
	}
	else{
		return false;
	}
	}
public static boolean isName(String parameter) {
	String reg = "^[a-zA-Z ]+$";
	if (parameter.matches(reg)) {
		return false;
	} else {
		return true;
	}
}
public static boolean isRole(String parameter) {
if (parameter.equalsIgnoreCase("admin")|| parameter.equalsIgnoreCase("user")){
	return false;
}else{
	return true;
}}

public static boolean isMobile(String val) {
	
	String mobile = "^[6-9]{1}[0-9]{9}$";
			if (val.matches(mobile)) {
			return false;
		} else{
			return true;
			
		}
		
}
public static boolean isPasswordLong(String parameter) {
	
		int length= parameter.trim().length();
	if (length<6){
		return true;
	}
	else{
	return false;
	}
	
}
public static boolean isDate(String val) throws ParseException {

	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	Date userDate = format.parse(val);
	Date todayDate = new Date();

	Calendar cal = Calendar.getInstance();
	cal.setTime(todayDate);
	cal.add(Calendar.YEAR, -17);

	// Calendar cal1 = Calendar.getInstance();
	Date beforeDate = cal.getTime();

	if (beforeDate.compareTo(userDate) == -1) {
		return true;
	} else {
		return false;
	}
}

}
