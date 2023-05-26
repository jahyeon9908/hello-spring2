package hello.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //webApplication에서 /hello로 들어오면 이 메소드가 호출된다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return  "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String hong, Model model){
        model.addAttribute("name1", hong);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 헤더부와 바디부가 있는데 바디부에 직접 데이터를 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //문자가 아닌 객체를 넘겨보자.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{ //static 클래스로 만들면 HelloController 클래스 안에서 Hello라는 클래스를 쓸 수 있다.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

//이전의 템플릿 엔진과의 차이는 뷰가 없다는 것 -> name에 넣은 값이 그대로 들어감