package com.ssm.nowgo.controller;

import com.ssm.nowgo.pojo.User;
import com.ssm.nowgo.service.LoginService;
import com.ssm.nowgo.util.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//登录注册
@Controller
@RequestMapping("/common")
public class LoginController {
        @Autowired
        private LoginService loginService;
        //注册
        @RequestMapping(value = "/register.html",method = RequestMethod.POST)
        @ResponseBody
        public Map<String, Object> regist(@RequestBody User user) {
            JSONObject user1=JSONObject.fromObject(user);
            User user2=(User) JSONObject.toBean(user1,User.class);
            List<User> user3 = loginService.getUserByPhone(user2.getPhone());
            for (User user4 : user3) {
                if (!user.getCode().equals(user4.getCode())){
                    return JsonUtil.getError(null);
                }
            }
            loginService.updateUser(user2);
            return JsonUtil.getSuccess();
        }


        @RequestMapping(value = "/send_register_code.html",method = RequestMethod.POST)
        @ResponseBody
        public Map<String,Object> send_register(@RequestBody User user1) throws Exception {
            System.out.println(user1.getPhone());
            String phone=user1.getPhone();
            int count = loginService.getUser(user1);
            if (count > 0) {
                return JsonUtil.getError("手机号已注册");
            }
            String code= CodeUtil.getCode();
            String url = "http://106.ihuyi.com/webservice/sms.php?method=Submit&";
            String param = "mobile="+phone+"&account=C46937551&password=22b16186acacb364b5a189c258c127ef&content=您的验证码是："+code+"。请不要把验证码泄露给其他人。";
            String res = MyHttpUtil.sendPost(url, param);
            User user = new User();
            user.setCode(code);
            user.setPhone(phone);
            loginService.addUser(user);
            Map<String,Object> result=new HashMap<>();
            result.put("success",true);
            result.put("result",true);
            result.put("error",null);

            return result;
    }
    @RequestMapping("/send_login_code.html")
    @ResponseBody
    public Map<String,Object> send_login(@RequestBody User user1) throws Exception {
        System.out.println(user1.getPhone());
        String phone=user1.getPhone();
        int count = loginService.getUser(user1);
        if (count == 0) {
            return JsonUtil.getError("手机号不存在，请注册");
        }
        String code= CodeUtil.getCode();
        String url = "http://106.ihuyi.com/webservice/sms.php?method=Submit&";
        String param = "mobile="+phone+"&account=C46937551&password=22b16186acacb364b5a189c258c127ef&content=您的验证码是："+code+"。请不要把验证码泄露给其他人。";
        String res = MyHttpUtil.sendGet(url, param);
        User user = new User();
        user.setCode(code);
        user.setPhone(phone);
        loginService.updateCode(user);
        Map<String,Object> result=new HashMap<>();
        result.put("success",true);
        result.put("result",true);
        result.put("error",null);
        return result;
    }

        @RequestMapping(value="/login.html",method = RequestMethod.POST)
        @ResponseBody
        public Map<String,Object> login(@RequestBody User user/*,@RequestParam String ip,
                                        @RequestParam String city,@RequestParam String province*/) {
            JSONObject user1=JSONObject.fromObject(user);
            User user2=(User) JSONObject.toBean(user1,User.class);
            int count= loginService.getUser(user2);
            if(count==0){
                return JsonUtil.getError("用户名不存在，请前去注册");
            }
            System.out.println(user2.toString());
            User user3 = loginService.getUserByUser(user2);
            if(user3==null){
                return JsonUtil.getError("密码或验证码不正确，请重新输入");
            }
           /*try{
               String url =
                       "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json";
               String ip= IPUtil.getUserIpAddr(request);
               System.out.println(ip);
               String json = HTTPUtil.httpGet(url, "ip=" + ip, "utf-8");
               System.out.println(json);
               Map<String, String> result = (Map<String, String>) JsonUtil.getObj(json, HashMap.class);
               String city = result.get("city");
               String province = result.get("province");
               System.out.println(city);
               System.out.println(province);
                Map<String,Object> loginLog = new HashMap<>();
                loginLog.put("city",city);
                loginLog.put("province",province);
                loginLog.put("ip",ip);
                loginLog.put("userId" ,user3.getId());
                loginService.addLoginLog(loginLog);
           }catch (Exception e){
                    e.printStackTrace();
           }*/
            try{System.out.println(user.getCity());
                Map<String,Object> loginLog = new HashMap<>();
                loginLog.put("city",user.getCity()==null?"北京":user.getCity());
                loginLog.put("province",user.getProvince()==null?"北京":user.getProvince());
                loginLog.put("ip",user.getIp()==null?"114.242.26.51":user.getIp());
                loginLog.put("userId" ,user3.getId());
                loginService.addLoginLog(loginLog);
            }catch (Exception e){
                e.printStackTrace();
            }
            Map<String,Object> result = new HashMap<>();
            result.put("success",true);
            result.put("result",true);
            result.put("userId",user3.getId());
            result.put("error",null);
            return result;
        }

        @RequestMapping("/logout.html")
        @ResponseBody
        public Map<String,Object> logout(HttpSession session) {
            session.invalidate();
            //session.setAttribute("SESSION_USER",null);
            return JsonUtil.getSuccessMessage("成功",null);
        }
    }


