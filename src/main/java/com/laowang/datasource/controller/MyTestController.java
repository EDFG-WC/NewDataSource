package com.laowang.datasource.controller;

import com.alibaba.fastjson.JSONObject;
import com.laowang.datasource.aop.LogTrack;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: wangc
 * @Date: 2021/6/2 13:34
 * @Description:
 */

@Controller
@RequestMapping("/test")
public class MyTestController {

	@ResponseBody
	@GetMapping("/testGet1")
	@LogTrack("testGet1 接口")
	public void testGet1(@RequestParam("userId") String userId, @RequestParam("toUserId") String toUserId) {

		System.out.println("已经进入GET测试接口,参数userId：" + userId+  "参数toUserId:"+toUserId);
	}


	@ResponseBody
	@GetMapping("/testGet2")
	@LogTrack("testGet2 接口")
	public void testGet2(@RequestParam Map map) {

		System.out.println("已经进入GET测试接口,参数：" + map.get("userId"));
	}

	@ResponseBody
	@PostMapping("/testPost1")
	@LogTrack("testPost1 接口")
	public void testPost1(@RequestBody Map map) {

		System.out.println("已经进入POST测试接口,参数：" + map.toString());
	}

	@ResponseBody
	@PostMapping("/testPost2")
	@LogTrack("testPost2 接口")
	public void testPost2(@RequestBody JSONObject jsonObject) {

		System.out.println("已经进入POST测试接口,参数：" + jsonObject.toString());
	}

}
