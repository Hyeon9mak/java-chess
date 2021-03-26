package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;
import chess.domain.board.position.Position;
import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BlackTurnTest {
    private BlackTurn blackTurn;

    @BeforeEach
    void setUp() {
        this.blackTurn = new BlackTurn(new Board());
    }

    @Test
    @DisplayName("흑색 차례에서 start 명령시 에러 반환")
    void testStartException() {
        assertThatThrownBy(() -> this.blackTurn.start()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("흑색 차례에서 흑색 말 move 명령 진행")
    void testMove() {
        Board board = this.blackTurn.board();

        assertThat(board.pieceByPosition(Position.of("a7"))).isEqualTo(Pawn.createBlack());
        assertThat(board.pieceByPosition(Position.of("a5"))).isEqualTo(Empty.create());

        this.blackTurn.moveIfValidColor(Position.of("a7"), Position.of("a5"));

        assertThat(board.pieceByPosition(Position.of("a7"))).isEqualTo(Empty.create());
        assertThat(board.pieceByPosition(Position.of("a5"))).isEqualTo(Pawn.createBlack());
    }

    @Test
    @DisplayName("흑색 차례에서 백색 말 move 명령시 예외반환")
    void testMoveException() {
        Board board = this.blackTurn.board();

        assertThat(board.pieceByPosition(Position.of("a2"))).isEqualTo(Pawn.createWhite());
        assertThat(board.pieceByPosition(Position.of("a4"))).isEqualTo(Empty.create());

        assertThatThrownBy(
            () -> this.blackTurn.moveIfValidColor(Position.of("a2"), Position.of("a4")))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("흑색 차례에서 passTurn 명령시 백색 차례 반환")
    void testPassTurn() {
        assertThat(this.blackTurn.passTurn()).isInstanceOf(WhiteTurn.class);
    }

    @Test
    @DisplayName("흑색 차례에서 ranks 명령시 ranks 반환")
    void testRanks() {
        List<Rank> ranks = this.blackTurn.ranks();
        assertThat(ranks).hasSize(8);
        for (Rank rank : ranks) {
            assertThat(rank.squares()).hasSize(8);
        }
    }

    @Test
    @DisplayName("흑색 차례에서 finishReason 명령시 에러 반환")
    void testFinishReasonException() {
        assertThatThrownBy(() -> this.blackTurn.finishReason()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("흑색 차례에서 winner 명령시 에러 반환")
    void testWinnerException() {
        assertThatThrownBy(() -> this.blackTurn.winner()).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("흑색 차례에서 end 명령시 End 상태 반환")
    void testEnd() {
        assertThat(this.blackTurn.end()).isInstanceOf(End.class);
    }

    @Test
    @DisplayName("흑색 차례에서 isInit 명령시 false 반환")
    void testIsInitTure() {
        assertThat(this.blackTurn.isInit()).isFalse();
    }

    @Test
    @DisplayName("흑색 차례에서 isRunning 명령시 true 반환")
    void testIsRunningTrue() {
        assertThat(this.blackTurn.isRunning()).isTrue();
    }

    @Test
    @DisplayName("흑색 차례에서 isFinished 명령시 false 반환")
    void testIsFinishedFalse() {
        assertThat(this.blackTurn.isFinished()).isFalse();
    }

    @Test
    @DisplayName("흑색 차례에서 isNotEnd 명령시 true 반환")
    void testIsNotEndTrue() {
        assertThat(this.blackTurn.isNotEnd()).isTrue();
    }
}
