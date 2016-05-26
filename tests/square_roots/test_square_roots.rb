require 'test/unit'
require_relative '../../square_roots/square_roots'

class Squareroots < Test::Unit::TestCase
  def test_case_1
    assert_equal(square_roots(5, 2), 10)
  end

  def test_case_2
    assert_equal(square_roots(50, 20), 582)
  end

  def test_case_3
    assert_equal(square_roots(500, 200), 49045)
  end

  def test_case_4
    assert_equal(square_roots(8274, 7858), 578951)
  end

  def test_case_5
    assert_equal(square_roots(2327, 4895), 10647)
  end

  def test_case_6
    assert_equal(square_roots(9354, 6748), 3908034)
  end
end
