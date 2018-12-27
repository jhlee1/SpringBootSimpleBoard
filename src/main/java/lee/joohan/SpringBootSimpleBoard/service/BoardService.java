package lee.joohan.SpringBootSimpleBoard.service;

import lee.joohan.SpringBootSimpleBoard.domain.Board;
import lee.joohan.SpringBootSimpleBoard.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1, pageable.getPageSize());

        return boardRepository.findAll(pageable);
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(new Board());
    }
}
