package com.citic.zktd.smart.security.service;

import com.citic.zktd.smart.security.repository.SimpleUserRepository;
import com.citic.zktd.smart.security.validate.ValidateCode;
import com.citic.zktd.smart.security.validate.ValidateCodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 通过用户名获取账号，密码和操作权限
 *
 * @author: 594781919@qq.com
 * @Date: 2018/4/8 15:26
 * @version: 1.0
 */
@Service("userDetailsService")
public class BiciUserDetailsService implements UserDetailsService {

    @Autowired
    private HttpSession session;

//    @Autowired
//    private UserApi userService;

    @Resource
    private SimpleUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        //TODO 可以通过feign从其他服务获取, 此处为了保持认证服务独立,所以使用数据库数据
//        com.citic.zktd.smart.entry.User u = userService.getUserByName(username);
        com.citic.zktd.smart.entry.User ex = new com.citic.zktd.smart.entry.User();
        ex.setUserName(username);
        Example example = Example.of(ex);
        Optional<com.citic.zktd.smart.entry.User> userOptional = userRepository.findOne(example);
        com.citic.zktd.smart.entry.User u = userOptional.get();
        //TODO 验证码及类型信息, 可从redis中获取
        ValidateCodeType im = (ValidateCodeType) session.getAttribute("ValidateType");
        ValidateCode sm = (ValidateCode) session.getAttribute("ValidateTypeCode");
        session.removeAttribute("ValidateType");
        session.removeAttribute("ValidateTypeCode");
        System.err.println("BiciUserDetailsService====================== 类型="+ im
                +"    >>>>  "+ (null==sm?"空":sm.getCode()));
        if (null != im && null != sm && "sms".equals(im.getParamNameOnValidate())) {
            //TODO 如果是短信登录需要使用手机从数据库中查询用户信息
            return new User(u.getUserName(),"{noop}", authorities);
        }
        // 返回在数据库中正确的用户信息，会与用户输入的数据进行比较
        // secret和密码从 Spring Security 5.0开始必须以 {加密方式}+加密后的密码 这种格式填写
        return new User(u.getUserName(),u.getUserPassword(), authorities);
    }
}
