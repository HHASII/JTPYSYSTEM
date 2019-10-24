package com.capgemini.jtp.mapper;

import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.vo.employee.request.EmpPrizeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeSearchVo;
import com.capgemini.jtp.vo.employee.request.EmployeeDeleteVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmpPrizeMapper {


    List<EmployeePrize> listPrize(@Param("prizeSearch") EmpPrizeSearchVo empPrizeSearchVo);

    Long countPrize(@Param("prizeSearch") EmpPrizeSearchVo empPrizeSearchVo);

    String getEmpName(Long empId);

    Integer insertPrize(@Param("prize") EmployeePrize employeePrize);

    Integer updatePrizeById(@Param("prize") EmployeePrize employeePrize);

    Integer deletePrizeById(@Param("prize") EmployeePrize employeePrize);

    Integer deletePrizeInfoByEmpId(@Param("prize") EmployeePrize employeePrize);

    List<EmployeePrize> getAllPrizes();

    Integer deletePrizeBatch(@Param("list") List<EmployeePrize> employeePrizeList);

    Integer insertPrizeBatch(@Param("prizes") List<EmployeePrize> employeePrizeList);

    Long getCreateUser(@Param("id") Long id);


}
