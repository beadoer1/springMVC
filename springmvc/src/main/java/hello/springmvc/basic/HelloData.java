package hello.springmvc.basic;

import lombok.Data;

@Data // @Getter, @Setter, @ToString, @RequiredArgsConstructor 를 포함
public class HelloData {
    private String username;
    private int age;
}
