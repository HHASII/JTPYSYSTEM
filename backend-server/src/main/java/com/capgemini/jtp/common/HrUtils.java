package com.capgemini.jtp.common;

import com.capgemini.jtp.entity.Hr;
import org.springframework.security.core.context.SecurityContextHolder;


public class HrUtils {

    public static Hr getCurrentHr() {
        return (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
