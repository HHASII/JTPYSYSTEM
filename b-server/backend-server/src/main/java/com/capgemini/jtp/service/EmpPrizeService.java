package com.capgemini.jtp.service;

import com.capgemini.jtp.entity.Employee;
import com.capgemini.jtp.entity.EmployeePrize;
import com.capgemini.jtp.vo.employee.request.DeleteBatchVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeDeleteVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeEditVo;
import com.capgemini.jtp.vo.employee.request.EmpPrizeSearchVo;
import com.capgemini.jtp.vo.employee.response.EmpPrizeResponseVo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmpPrizeService {

    public EmpPrizeResponseVo listPrize(EmpPrizeSearchVo empPrizeSearchVo);

    public Integer updatePrizeById(EmpPrizeEditVo empPrizeEditVo);

    public Integer addPrize(EmpPrizeEditVo empPrizeEditVo);

    public Integer deletePrizeById(EmpPrizeDeleteVo empPrizeDeleteVo);

    public Integer deletePrizeInfoByEmpId(Employee employee);

    public ResponseEntity<byte[]> exportAllPrizes();

    Integer deletePrizeBatch(DeleteBatchVo deleteBatchVo, Long operationUserId);

    public Integer importPrizesFromExcel(List<EmployeePrize> employeePrizeList);


}
