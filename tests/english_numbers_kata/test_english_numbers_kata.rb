require 'test/unit'
require_relative '../../english_numbers_kata/english_numbers_kata'

class EnglishNumbersKataTest < Test::Unit::TestCase
  def test_initialize
    assert_equal(defined?(EnglishNumbers.new(37545)).!.!, true)
  end

  def test_case_1
    assert_equal(EnglishNumbers.new(3).to_english, "three")
    assert_equal(EnglishNumbers.new(8).to_english, "eight")
    assert_equal(EnglishNumbers.new(0).to_english, "zero")
  end

  def test_case_2
    assert_equal(EnglishNumbers.new(33).to_english, "thirty-three")
    assert_equal(EnglishNumbers.new(42).to_english, "forty-two")
    assert_equal(EnglishNumbers.new(97).to_english, "ninety-seven")
  end

  def test_case_3
    assert_equal(EnglishNumbers.new(10).to_english, "ten")
    assert_equal(EnglishNumbers.new(13).to_english, "thirteen")
  end
end
