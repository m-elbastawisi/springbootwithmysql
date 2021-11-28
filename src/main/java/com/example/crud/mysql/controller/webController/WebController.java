package com.example.crud.mysql.controller.webController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.crud.mysql.model.form.EmployeeVO;
import com.example.crud.mysql.service.inf.EmployeeServiceInf;
import com.example.crud.mysql.utils.Mapper;

import org.springframework.ui.Model;

@Controller
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	EmployeeServiceInf employeeServiceInf ;
	
	@GetMapping("/")
	public String main(Model model,EmployeeVO employeeVo) {
	 	model.addAttribute("list", employeeServiceInf.getAll());
	 	model.addAttribute("employee", null);
        return "welcome";
    }
	
	@PostMapping("/saveemp")
	public String saveEmp(@ModelAttribute EmployeeVO employeeVO ,Model model) {
		 logger.info("Entering into saveEmp method ");
		 logger.info("Entering into saveEmp method "+employeeVO.getId());
		 employeeServiceInf.save(Mapper.converFormToEntity(employeeVO));
		 model.addAttribute("list", employeeServiceInf.getAll());
		 logger.info("existing into saveEmp method ");
	    return "welcome";
	}
	
	
	 @GetMapping("/update/{id}")
	  public String update(@PathVariable(value="id") long id,Model model,EmployeeVO employeeVo) {
		 logger.info("Entering into update method ");
		  model.addAttribute("employee", Mapper.converEntityToForm(employeeServiceInf.findById(id).getBody().getResult()));
		  model.addAttribute("list", employeeServiceInf.getAll());
		  logger.info("existing into update method ");
	    return "welcome";
	  }
	 
	 @GetMapping("/delete/{id}")
	  public String deleteById(@PathVariable(value="id") long id,Model model,EmployeeVO employeeVo) {
		 logger.info("Entering into update method ");
		 employeeServiceInf.deleteById(id);
		  model.addAttribute("list", employeeServiceInf.getAll());
		  logger.info("existing into update method ");
	    return "welcome";
	  }
	 

	
}
