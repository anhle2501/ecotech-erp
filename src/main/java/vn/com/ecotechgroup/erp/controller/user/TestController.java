package vn.com.ecotechgroup.erp.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("/ingredients")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<String> rep() {
		System.out.println("111111111111111");
		ResponseEntity<String> p = new ResponseEntity<String>("get Ingredient", HttpStatus.OK);
		return p;
	}
}
