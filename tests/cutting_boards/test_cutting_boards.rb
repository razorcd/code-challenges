require 'test/unit'
require_relative '../../cutting_boards/cutting_boards'

class CuttingBoardsTest < Test::Unit::TestCase
  def board1
    Board.new(mcosts: [2], ncosts: [1])
  end
  def board2
    Board.new(mcosts: [2,1,3,1,4], ncosts: [4,1,2])
  end

  def test_initialize
    board= board1
    assert_equal(defined?(board).!.!, true)
  end

  def test_cut1
    board= board1
    assert_equal(board.cut, 4)
  end

  def test_cut2
    board= board2
    assert_equal(board.cut, 42)
  end


end
