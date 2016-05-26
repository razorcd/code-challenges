require 'test/unit'
require_relative '../../bigger_is_greater/bigger_is_greater'

class BiggerIsGreaterTests < Test::Unit::TestCase
  def test_case_1
    assert_equal(BiggerIsGreater.new("hefg").greater, "hegf")
  end

  def test_case_2
    assert_equal(BiggerIsGreater.new("phogffol").greater, "phogflfo")
  end

  def test_case_3
    assert_equal(BiggerIsGreater.new("dkhc").greater, "hcdk")
  end

  def test_case_4
    assert_equal(BiggerIsGreater.new("zyyvvutt").greater, nil)
  end

  def test_case_5
    assert_equal(BiggerIsGreater.new("bb").greater, nil)
  end

  def test_combinations
    perm= []
    "1213".chars.permutation {|p| perm << p.join}
    assert_equal(perm.sort, BiggerIsGreater.new("1213").send(:combinations).sort)
  end
end
