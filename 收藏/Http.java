public static String sendPostAddGroup(String username,String groupid,Map map) {
    	String url = "https://a1.easemob.com/"+Const.org_name+"/"+Const.app_name+"/chatgroups/"+groupid+"/users/"+username;
    	String res = "";
    	try {
    	HttpClient client = HttpClientBuilder.create().build();
		//此处可设置多种方式 Post Get Put  Delet
    	HttpPost post = new HttpPost(url);
    	post.setHeader("Content-type", "application/json;charset=UTF-8");
    	String Property = "Bearer "+map.get("access_token");
    	post.setHeader("Authorization",Property);
    	HttpResponse response = client.execute(post);
    	int code = response.getStatusLine().getStatusCode();
    	System.out.println("Response Code:"+code);
    	BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    	if(code==200){
    		res ="01";//成功
    	}else{
    		res = "00";//失败
    	}
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	result.append(line);
    	}
    	System.out.println("result:"+result);
	    } catch (Exception e) {
	    	res="00";
			e.printStackTrace();
		}
    	return res;
    } 