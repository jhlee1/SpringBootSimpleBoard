package lee.joohan.SpringBootSimpleBoard;

import lee.joohan.SpringBootSimpleBoard.domain.Board;
import lee.joohan.SpringBootSimpleBoard.domain.User;
import lee.joohan.SpringBootSimpleBoard.domain.enums.BoardType;
import lee.joohan.SpringBootSimpleBoard.repository.BoardRepository;
import lee.joohan.SpringBootSimpleBoard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringBootSimpleBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSimpleBoardApplication.class, args);
    }

    @Bean // 스프링은 @Bean annotation이 붙은 method에 parameter에 DI한다.
    public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception {
        return args -> {
            User user =userRepository.save(User.builder()
                    .name("tester")
                    .password("password")
                    .email("tester@test.com")
                    .createdAt(LocalDateTime.now())
                    .build());

            IntStream.rangeClosed(1, 200).forEach( i ->
                    boardRepository.save(Board.builder()
                            .title("Random title" + i)
                            .subTitle("Order" + i)
                            .content("Content")
                            .boardType(BoardType.FREE)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build())
            );
        };
    }
}

