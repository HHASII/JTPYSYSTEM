package com.capgemini.jtp.mapper;
import com.capgemini.jtp.vo.employee.request.StudyDeclarationEditVo;
import org.apache.ibatis.annotations.Param;
import com.capgemini.jtp.entity.PasswordChange;
import com.capgemini.jtp.vo.employee.request.PasswordChangeVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import sun.security.util.Password;

import java.util.Date;

@Repository
public interface PasswordVerCodeMapper {
    Long insertVerCode(@Param("passwordChange") PasswordChange passwordChange);

    Long getId(int vericode);

    Integer getVerCode(@Param("passwordChangeVo") PasswordChangeVo passwordChangeVo);

    String getUseremail(@Param("passwordChangeVo") PasswordChangeVo passwordChangeVo);

    Integer updateUserPassword(@Param("passwordChangeVo") PasswordChangeVo passwordChangeVo);

    Date getVeriCodenCreateTime(@Param("passwordChangeVo") PasswordChangeVo passwordChangeVo);
}
