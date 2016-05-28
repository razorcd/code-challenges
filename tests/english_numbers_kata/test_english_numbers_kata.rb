require 'test/unit'
require_relative '../../english_numbers_kata/english_numbers_kata'

class EnglishNumbersKataTest < Test::Unit::TestCase
  def test_initialize
    assert_equal(defined?(EnglishNumbers.new(37545)).!.!, true)
  end

  def test_case_digits
    assert_equal(EnglishNumbers.new(3).to_english, "three")
    assert_equal(EnglishNumbers.new(8).to_english, "eight")
    assert_equal(EnglishNumbers.new(0).to_english, "zero")
  end

  def test_case_tents
    assert_equal(EnglishNumbers.new(50).to_english, "fifty")
    assert_equal(EnglishNumbers.new(33).to_english, "thirty-three")
    assert_equal(EnglishNumbers.new(42).to_english, "forty-two")
    assert_equal(EnglishNumbers.new(97).to_english, "ninety-seven")
  end

  def test_case_10_to_19
    assert_equal(EnglishNumbers.new(10).to_english, "ten")
    assert_equal(EnglishNumbers.new(13).to_english, "thirteen")
    assert_equal(EnglishNumbers.new(19).to_english, "nineteen")
  end

  def test_case_hundreds
    assert_equal(EnglishNumbers.new(100).to_english, "one hundred")
    assert_equal(EnglishNumbers.new(103).to_english, "one hundred three")
    assert_equal(EnglishNumbers.new(316).to_english, "three hundred sixteen")
    assert_equal(EnglishNumbers.new(872).to_english, "eight hundred seventy-two")
  end

  def test_case_thousands
    assert_equal(EnglishNumbers.new(1000).to_english, "one thousand")
    assert_equal(EnglishNumbers.new(1035).to_english, "one thousand thirty-five")
    assert_equal(EnglishNumbers.new(4321).to_english, "four thousand three hundred twenty-one")
    assert_equal(EnglishNumbers.new(8001).to_english, "eight thousand one")
    assert_equal(EnglishNumbers.new(9111).to_english, "nine thousand one hundred eleven")
  end

  def test_case_tents_and_hundreds_of_thousands
    assert_equal(EnglishNumbers.new(13_000).to_english, "thirteen thousand")
    assert_equal(EnglishNumbers.new(41_035).to_english, "forty-one thousand thirty-five")
    assert_equal(EnglishNumbers.new(361_315).to_english, "three hundred sixty-one thousand three hundred fifteen")
  end

  def test_case_tents_and_hundreds_of_millions_and_over
    assert_equal(EnglishNumbers.new(1_000_000).to_english, "one million")
    assert_equal(EnglishNumbers.new(13_511_321).to_english, "thirteen million five hundred eleven thousand three hundred twenty-one")
    assert_equal(EnglishNumbers.new(541_101_035).to_english, "five hundred forty-one million one hundred one thousand thirty-five")
  end
end
