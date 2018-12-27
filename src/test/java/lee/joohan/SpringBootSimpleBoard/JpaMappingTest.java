package lee.joohan.SpringBootSimpleBoard;

import lee.joohan.SpringBootSimpleBoard.domain.Board;
import lee.joohan.SpringBootSimpleBoard.domain.User;
import lee.joohan.SpringBootSimpleBoard.domain.enums.BoardType;
import lee.joohan.SpringBootSimpleBoard.repository.BoardRepository;
import lee.joohan.SpringBootSimpleBoard.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTitleForTest = "Test title for JPA Test";
    private final String userNameForTest = "tester";
    private final String userEmailForTest = "test@test.com";
    private final String userPasswordForTest = "myBirthDay";
    private final String boardSubTitleForTest = "subTitle for JPA Test";
    private final String boardContentForTest = "Random content for JPA Test";
    private final BoardType boardTypeForTest = BoardType.FREE;
    private final LocalDateTime currentTime = LocalDateTime.now();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
                .name(userNameForTest)
                .password(userPasswordForTest)
                .email(userEmailForTest)
                .createdAt(currentTime)
                .build()
        );

        boardRepository.save(Board.builder()
                .title(boardTitleForTest)
                .subTitle(boardSubTitleForTest)
                .content(boardContentForTest)
                .boardType(boardTypeForTest)
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .user(user)
                .build()
        );
    }

    @Test
    public void readTest() {
        User user = userRepository.findByEmail(userEmailForTest);
        Board board = boardRepository.findByUser(user);

        assertThat(user.getName(), is(userNameForTest));
        assertThat(user.getPassword(), is(userPasswordForTest));
        assertThat(user.getEmail(), is(userEmailForTest));
        assertThat(user.getCreatedAt(), is(currentTime));

        assertThat(board.getTitle(), is(boardTitleForTest));
        assertThat(board.getSubTitle(), is(boardSubTitleForTest));
        assertThat(board.getBoardType(), is(boardTypeForTest));
        assertThat(board.getContent(), is(boardContentForTest));
        assertThat(board.getCreatedAt(), is(currentTime));
        assertThat(board.getUpdatedAt(), is(currentTime));
    }
}
