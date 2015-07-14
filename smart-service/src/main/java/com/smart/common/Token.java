package com.smart.common;

public class Token {
	static private String secretKey="tour_key";
	static private String splitChar="#";

	static public String getToken(String user, String role,   String mac) {
		String ret_token="";	 
		String token_src=user+splitChar+role+splitChar+mac ;
		String token_src_md5=token_src+splitChar+secretKey ;
		String token_md5 = Md5Func.md5(token_src_md5.getBytes());
	//	ret_token=token_src+splitChar+token_md5;
		return token_md5;
	}
	
	static public Boolean isValid(String token) {
		Boolean ret=false;
		if (token==null || token.isEmpty()){
			return ret;
		}
		
		String [] strList = token.split(splitChar);		
		if (strList.length ==4){
			String token_src=strList[0]+splitChar+strList[1]+splitChar+strList[2]+splitChar+secretKey ;
			String token_md5 = Md5Func.md5(token_src.getBytes());
			if (token_md5.equals(strList[3])){
				ret=true;
			}
		}	 
		return ret;
	} 
	
	public static boolean isValid(String userKey, String t, String m) {
		Boolean ret=false;
		if (t==null || t.isEmpty()){
			return ret;
		}
		if (m==null || m.isEmpty()){
			return ret;
		}
		
		ret = (m.equalsIgnoreCase(getTokenM(t,userKey))?true:false) ;
		return ret;
	}

	private static String getTokenM(String t,String userKey) {
    	String lastChar = t.substring(t.length()-1,t.length());//取最后一个字符
		int i=Integer.parseInt(lastChar);
		String md5String = userKey +t.substring(i);    
		return Md5Func.md5(md5String.getBytes());//求md5值
	}
	
	public static void main(String[] args){
		 
		boolean dddxx =Token. isValid("", "1436064222181",  "1800d8fe88068e1274ad0a0ab117eb53");
		
		
		String   result ="hlj1#2#1389686200545#EDA66D4B8C9826B8459982E39F7246B5";
		//Boolean ret= Token.isValid(result);
	//	System.out.println(ret);
		String x =Token. getToken("shanghu1", "2",  "22");
		String xx =Token. getToken("jiudian1", "3",  "333");
		String xdx =Token. getToken("jiudian2", "3",  "3333");
		
		String   result22=Md5Func.md5(result.getBytes())
				
				;		
		String   result233=Md5Func.sha1(result.getBytes());	
		
		
	}

	
}
