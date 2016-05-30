require 'test/unit'
require_relative '../../custom_set/custom_set'

class CustomSetTest < Test::Unit::TestCase
  def empty_custom_set
    CustomSet.new
  end

  def one_custom_set
    c= CustomSet.new()
    c.add("a")
    c
  end

  def many_custom_set
    c= CustomSet.new()
    c.add("a")
    c.add("b")
    c
  end

  def test_empty_set
    assert_equal(empty_custom_set.is_empty?, true)
    assert_equal(one_custom_set.is_empty?, false)
  end

  def test_size
    assert_equal(empty_custom_set.size, 0)
    assert_equal(one_custom_set.size, 1)
    assert_equal(many_custom_set.size>1, true)
  end

end
