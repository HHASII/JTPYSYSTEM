package com.capgemini.jtp.common;

import com.capgemini.jtp.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description: TODO
 * @Classname : HrUtils
 * @author: Jason Jin
 * @date: 2019/5/19 11:47 PM
 */
public class HrUtils {

    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
