class Frame
  TOTAL_PINS = 10

  def initialize last: false
    @allowed_trows = 2
    @last = last
    @score = []
    reset_up_pins
    @throws_count = 0
    @expected_extra_throws_count = 0
  end

  def throw! knocked_pins:
    raise(FrameError, "can't throw anymore") if over?
    raise(FrameError, "can't knock down more pins than are still standing") if knocked_pins > @up_pins_count

    @up_pins_count -= knocked_pins
    @score << knocked_pins
    @throws_count += 1

    allow_one_more_throw if (strike? || double_strike? || spare?) && @last
    update_expected_extra_throws_count if over? && @last.!
  end

  def update_extra_score added_points:
    return false if @expected_extra_throws_count == 0
    @score << added_points
    @expected_extra_throws_count -= 1
    true
  end

  def score
    @score.inject(&:+).to_i
  end

  def over?
    @up_pins_count == 0 || @throws_count == @allowed_trows
  end

private

  def strike?
    @throws_count == 1 && (@score[0] == TOTAL_PINS)
  end

  def double_strike?
    @throws_count == 2 && (@score[0] == TOTAL_PINS) && (@score[1] == TOTAL_PINS)
  end

  def spare?
    @throws_count == 2 && (@score[0]+@score[1] == TOTAL_PINS)
  end

  def update_expected_extra_throws_count
    @expected_extra_throws_count = 2 if strike?
    @expected_extra_throws_count = 1 if spare?
  end

  def reset_up_pins
    @up_pins_count = TOTAL_PINS
  end

  def allow_one_more_throw
    reset_up_pins
    @allowed_trows = 3
  end
end

class FrameError < StandardError
end
