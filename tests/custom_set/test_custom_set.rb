require 'test/unit'
require_relative '../../custom_set/custom_set'

class CustomSetTest < Test::Unit::TestCase
  def empty_custom_set
    CustomSet.new
  end

  def one_custom_set
    cs = CustomSet.new
    cs.add("a")
    cs
  end

  def many_custom_set
    cs = CustomSet.new
    cs.add("a")
    cs.add("b")
    cs
  end

  def test_empty_set
    assert_equal(empty_custom_set.is_empty?, true)
    assert_equal(one_custom_set.is_empty?, false)
  end

  def test_size
    assert_equal(empty_custom_set.size, 0)
    assert_equal(one_custom_set.size, 1)
    assert_equal(many_custom_set.size > 1, true)
  end

  def test_contains
    cs = CustomSet.new

    assert_equal(cs.contains("a"), false)
    assert_equal(cs.contains("z"), false)
    cs.add("a")
    assert_equal(cs.contains("a"), true)
    assert_equal(cs.contains("z"), false)
  end

  def test_add_ignores_duplicates
    cs = CustomSet.new
    cs.add("a")
    cs.add("a")

    assert_equal(cs.contains("a"), true)
    assert_equal(cs.size, 1)
  end

  def test_remove
    cs = CustomSet.new
    cs.add("a")
    cs.remove("a")

    assert_equal(cs.size, 0)
    assert_equal(cs.contains("a"), false)
  end

  def test_remove_non_existing_element
    c = CustomSet.new()
    c.add("a")
    c.remove("a")
    c.remove("b")

    assert_equal(c.size, 0)
    assert_equal(c.contains("a"), false)
  end
end
