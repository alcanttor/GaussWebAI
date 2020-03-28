package com.mg.gaussWorker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.mg.gaussWorker.model.Actions;
import com.mg.gaussWorker.model.RequestData;


public class Test {

	public static void main(String gg[])
	{
		RequestData dd = new RequestData();
		List<Actions> s = new ArrayList<>();
		s.add(Actions.EMAIL);
		HashMap<String, String> ss = new HashMap<String,String>();
		ss.put("to","luna.varun@gmail.com");
		ss.put("text","role change from a to b");
		ss.put("subject", "role change notification");
		dd.setActions(s);
		dd.setMetaData(ss);
		System.out.println("json test : "+new Gson().toJson(dd));
	}
}
